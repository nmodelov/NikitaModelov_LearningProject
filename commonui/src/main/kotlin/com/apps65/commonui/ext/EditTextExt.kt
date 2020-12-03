package com.apps65.commonui.ext

import android.widget.EditText
import androidx.annotation.StringRes
import com.apps65.commonui.DistinctTextWatcher
import com.apps65.commonui.FixedUnderscoreDigitSlotParser
import com.google.android.material.textfield.TextInputLayout
import ru.tinkoff.decoro.FormattedTextChangeListener
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import java.util.WeakHashMap

private val formatters = WeakHashMap<EditText, FormatWatcher>()

/**
 * Register a callback to be invoked when focus of this view is lost.
 *
 * @param action The callback that will run.
 */
fun EditText.doOnFocusLost(action: () -> Unit) {
    setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            action()
        }
    }
}

/**
 * Adds a TextWatcher to the list of those whose methods are called
 * whenever this TextView's text changes.
 *
 * @param action The callback that will run.
 * @receiver Target [EditText].
 */
fun EditText.doOnTextUpdated(action: (String) -> Unit) {
    addTextChangedListener(DistinctTextWatcher(action))
}

fun EditText.installFormatter(
    mask: MaskImpl,
    onTextUpdated: ((String) -> Unit)? = null
) {
    val formatWatcher = MaskFormatWatcher(mask)
    formatWatcher.installOn(this)
    if (onTextUpdated != null) {
        formatWatcher.setCallback(object : FormattedTextChangeListener {
            private var lastValue: String? = null

            override fun beforeFormatting(oldValue: String?, newValue: String?): Boolean = false

            override fun onTextFormatted(formatter: FormatWatcher, newFormattedText: String?) {
                val unformattedString = formatter.mask.toUnformattedString()
                if (lastValue != unformattedString) {
                    lastValue = unformattedString
                    onTextUpdated.invoke(unformattedString)
                }
            }
        })
    }
    formatters[this] = formatWatcher
}

fun EditText.installFormatter(
    rawMask: String,
    onTextUpdated: ((String) -> Unit)? = null
) {
    val slots = FixedUnderscoreDigitSlotParser().parseSlots(rawMask)
    val mask = MaskImpl.createTerminated(slots)
    installFormatter(mask, onTextUpdated)
}

fun EditText.textWithoutMask(mask: MaskImpl): String {
    mask.insertFront(this.text)
    return mask.toUnformattedString()
}

/**
 * Update EditText's text if is not equals given value.
 *
 * @receiver Target [EditText].
 */
fun EditText.updateText(newText: String) {
    formatters[this]?.let {
        if (it.mask.toUnformattedString() != newText) {
            setText(newText)
        }
    } ?: run {
        if (text.toString() != newText) {
            setText(newText)
        }
    }
}

/**
 * Sets an error message that will be displayed below our [EditText]. If the [error]
 * is 0, the error message will be cleared.
 *
 * @param error Error message string resId to display, or 0 to clear.
 * @receiver Target [TextInputLayout].
 */
fun TextInputLayout.setError(@StringRes error: Int) {
    if (error == 0) {
        isErrorEnabled = false
    } else {
        this.error = context.getString(error)
    }
}