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
 * @deprecated Use [EditText.newText] instead.
 */
@Deprecated("Deprecated in favor of newText variable usage")
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
 * Update EditText's text if is not equals given value. Returns unformatted value.
 */
var EditText.newText: String
    @Deprecated(
        message = "Use unformattedText",
        replaceWith = ReplaceWith("unformattedText"),
        level = DeprecationLevel.ERROR
    )
    get() = formatters[this]?.mask?.toUnformattedString() ?: text.toString()
    set(value) {
        formatters[this]?.let {
            if (it.mask.toUnformattedString() != value) {
                setText(value)
            }
        } ?: run {
            if (text.toString() != value) {
                setText(value)
            }
        }
    }

val EditText.unformattedText: String
    get() = formatters[this]?.mask?.toUnformattedString() ?: text.toString()

val EditText.formattedText: String
    get() = formatters[this]?.mask?.toString() ?: text.toString()

val TextInputLayout.unformattedText: String
    get() = editText?.unformattedText ?: throw IllegalStateException("TextInputLayout hasn't EditText")

val TextInputLayout.formattedText: String
    get() = editText?.formattedText ?: throw IllegalStateException("TextInputLayout hasn't EditText")

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

/**
 * Sets an error message that will be displayed below our [EditText]. If the [error] is null,
 * the error message will be cleared.
 *
 * @param error Error message string resId to display, or null to clear.
 * @param errorEnabled Whether the error functionality is enabled or not in this layout. Enabling this functionality
 * before setting an error message will mean that this layout will not change size when an error is displayed.
 * @receiver Target [TextInputLayout].
 */
fun TextInputLayout.setError(@StringRes error: Int? = null, errorEnabled: Boolean = true) {
    this.error = if (error == null) {
        null
    } else {
        context.getString(error)
    }
    if (isErrorEnabled != errorEnabled) {
        isErrorEnabled = errorEnabled
    }
}
