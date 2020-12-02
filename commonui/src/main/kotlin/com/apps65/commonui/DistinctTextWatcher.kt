package com.apps65.commonui

import android.text.Editable
import android.text.TextWatcher

class DistinctTextWatcher(private val action: (String) -> Unit) : TextWatcher {
    private var lastValue: String? = null

    override fun afterTextChanged(s: Editable?) {
        val content = if (s.isNullOrEmpty()) {
            ""
        } else {
            s.toString()
        }
        if (lastValue != content) {
            lastValue = content
            action(content)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // NOP
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // NOP
    }
}
