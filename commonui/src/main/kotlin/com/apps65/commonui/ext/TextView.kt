package com.apps65.commonui.ext

import android.widget.TextView

/**
 * Update TextView's text if is not equals given value.
 */
var TextView.updateTextOn: CharSequence
    get() = text
    set(value) {
        if (text.toString() == value) {
            return
        }
        text = value
    }
