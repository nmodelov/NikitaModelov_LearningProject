package ${packageName}.domain.${featurePackage}.store

import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvi.common.IntentExecutorDelegate
import com.apps65.mvi.common.SuspendDelegationExecutor
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Action
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Intent
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Label
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.State
import ${packageName}.domain.${featurePackage}.store.${featureName}StoreFactory.Result
import com.arkivanov.mvikotlin.core.store.Executor
import javax.inject.Inject

internal typealias ${featureName}Executor = () -> Executor<Intent, Action, State, Result, Label>
internal typealias ${featureName}ExecutorDelegate = IntentExecutorDelegate<Intent, State, Result, Label>

internal class ${featureName}ExecutorsFactory @Inject constructor(
    private val dispatchersProvider: DispatchersProvider
) {
    fun create(): ${featureName}Executor = {
        object : SuspendDelegationExecutor<Intent, Action, State, Result, Label>(
            dispatchersProvider
        ) {
            /**
             * Если обработка интента не требует доп. зависимостей и какой-то сложной логики
             * то лучше вынести в отдельный метод. В примере - [executeIncrement]
             * в when блоке - только однострочные конструкции.
             */
            override suspend fun executeIntent(intent: Intent, getState: () -> State) {
                when (intent) {

                }
            }

            override suspend fun executeAction(action: Action, getState: () -> State) {
                when (action) {

                }
            }
        }
    }
}
