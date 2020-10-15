package com.apps65.mvi.saving

import android.os.Bundle
import android.os.Parcelable
import com.arkivanov.mvikotlin.core.store.Store
import java.lang.ref.WeakReference

class SavedStateKeeperImpl : SavedStateKeeper, StateBundleKeeper {
    private val storeContainers: MutableMap<String, StoreContainer<*, *>> = mutableMapOf()
    private var savedInstanceState: Bundle? = null

    override fun <P : Parcelable> get(key: String): P? {
        val bundle = savedInstanceState
        return bundle?.getParcelable<P>(key)
    }

    override fun <S : Store<*, *, *>, P : Parcelable> register(
        store: S,
        key: String,
        stateProvider: S.() -> P
    ) {
        storeContainers[key] =
            StoreContainer(
                store,
                stateProvider
            )
    }

    override fun restoreState(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
    }

    @Suppress("UNCHECKED_CAST")
    override fun saveState(outState: Bundle) {
        val iterator =
            (storeContainers as MutableMap<String, StoreContainer<Store<Any, Any, Any>, Parcelable>>).iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val store = entry.value.storeWeakReference.get()
            val isDisposed = store?.isDisposed ?: true
            if (isDisposed) {
                iterator.remove()
            } else {
                val value = entry.value.stateProvider.invoke(store!!)
                outState.putParcelable(entry.key, value)
            }
        }
    }

    private class StoreContainer<S : Store<*, *, *>, P : Parcelable>(
        store: S,
        val stateProvider: S.() -> P
    ) {
        val storeWeakReference: WeakReference<S> = WeakReference(store)
    }
}
