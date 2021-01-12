package com.apps65.mvitemplate.presentation.di

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.savedstate.SavedStateRegistryOwner
import com.apps65.mvi.saving.SavedStateKeeper
import com.arkivanov.mvikotlin.core.store.Store

class CommonSavedStateKeeper(savedStateRegistryOwner: SavedStateRegistryOwner) : SavedStateKeeper {
    private val savedStateRegistry = savedStateRegistryOwner.savedStateRegistry
    private val stateKey: String = savedStateRegistryOwner.javaClass.name

    override fun <P : Parcelable> get(key: String): P? {
        return savedStateRegistry.consumeRestoredStateForKey(stateKey)
            ?.getParcelable<P>(key)
    }

    override fun <S : Store<*, *, *>, P : Parcelable> register(
        store: S,
        key: String,
        stateProvider: S.() -> P
    ) {
        savedStateRegistry.registerSavedStateProvider(stateKey) {
            bundleOf(key to stateProvider(store))
        }
    }

    override fun unregister() {
        savedStateRegistry.unregisterSavedStateProvider(stateKey)
    }
}
