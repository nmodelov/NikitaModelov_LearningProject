package com.apps65.commonui.ext

import android.content.Context
import androidx.viewbinding.ViewBinding

val ViewBinding.context: Context get() = root.context
