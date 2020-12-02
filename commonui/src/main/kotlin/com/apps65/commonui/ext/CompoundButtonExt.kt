package com.apps65.commonui.ext

import android.widget.CompoundButton
import com.apps65.commonui.DistinctCheckedChangeListener

fun CompoundButton.doOnCheckedUpdated(action: (checked: Boolean) -> Unit) {
    setOnCheckedChangeListener(DistinctCheckedChangeListener(action))
}

fun CompoundButton.setEventCheckedChangeListener(block: (isChecked: Boolean) -> Unit) {
    this.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
        private var lastValue: Boolean? = null
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            if (lastValue != isChecked) {
                lastValue = isChecked
                block.invoke(isChecked)
            }
        }
    })
}
