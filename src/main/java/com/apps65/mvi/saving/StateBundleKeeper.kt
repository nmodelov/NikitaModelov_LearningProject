package com.apps65.mvi.saving

import android.os.Bundle

interface StateBundleKeeper {
    fun saveState(outState: Bundle)
    fun restoreState(savedInstanceState: Bundle?)
}
