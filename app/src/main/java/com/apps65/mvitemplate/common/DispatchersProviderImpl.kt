package com.apps65.mvitemplate.common

import kotlinx.coroutines.Dispatchers

internal object DispatchersProviderImpl : DispatchersProvider {
    override val default = Dispatchers.Default
    override val main = Dispatchers.Main
    override val unconfined = Dispatchers.Unconfined
    override val io = Dispatchers.IO
}
