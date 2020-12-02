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

fun EditText.doOnFocusLost(action: () -> Unit) {
    setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            action()
        }
    }
}

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

fun TextInputLayout.setError(@StringRes error: Int) {
    if (error == 0) {
        isErrorEnabled = false
    } else {
        this.error = context.getString(error)
    }
}
