package ${packageName}.domain.${featurePackage}.store

import com.apps65.mvi.saving.SavedStateKeeper
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Intent
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Label
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.State
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import javax.inject.Inject

private const val ${featureName}_STORE_STATE = "${featureName}_store_state"

internal class ${featureName}StoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val stateKeeper: SavedStateKeeper,
    private val executorsFactory: ${featureName}ExecutorsFactory
) {

    fun create(): ${featureName}Store {
        val storeDelegate = storeFactory.create(
            name = "${featureName}Store",
            initialState = getInitialState(),
            executorFactory = executorsFactory.create(),
            reducer = ${featureName}Reducer(),
            bootstrapper = bootstrapper
        )

        return object : ${featureName}Store, Store<Intent, State, Label> by storeDelegate {
            override fun dispose() {
                storeDelegate.dispose()
                stateKeeper.unregister()
            }
        }.also { registerStateKeeper(it) }
    }

    private val bootstrapper = object : SuspendBootstrapper<${featureName}Store.Action>() {
        override suspend fun bootstrap() {

        }
    }

    private fun getInitialState(): State {
        val savedState = stateKeeper.get<State>(${featureName}_STORE_STATE)
        return savedState ?: State()
    }

    private fun registerStateKeeper(store: ${featureName}Store) {
        stateKeeper.register(store, ${featureName}_STORE_STATE) {
            this.state // in this case just providing the state without changes
            // you can provide provide a state which is different from the current state of store,
            // for example if you don't want to show loading after restoring the state
        }
    }

    sealed class Result {

    }
}
