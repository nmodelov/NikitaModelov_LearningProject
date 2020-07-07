package com.apps65.mvikotlinrxjava

import com.arkivanov.mvikotlin.rx.Disposable
import io.reactivex.rxjava3.disposables.Disposable as ReaktiveDisposable

internal fun Disposable.toReaktiveDisposable(): ReaktiveDisposable =
    object : ReaktiveDisposable {
        override fun isDisposed(): Boolean {
            return this@toReaktiveDisposable.isDisposed
        }

        override fun dispose() {
            this@toReaktiveDisposable.dispose()
        }
    }
