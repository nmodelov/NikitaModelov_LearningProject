package com.apps65.commonui

import android.widget.CompoundButton

class DistinctCheckedChangeListener(
    private val action: (checked: Boolean) -> Unit
) : CompoundButton.OnCheckedChangeListener {
    private var lastValue: Boolean? = null

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (lastValue != isChecked) {
            lastValue = isChecked
            action(isChecked)
        }
    }
}
