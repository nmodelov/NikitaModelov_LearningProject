package ${packageName}.domain.${featurePackage}.store

import ${packageName}.domain.${featurePackage}.store.${featureName}Store.State
import ${packageName}.domain.${featurePackage}.store.${featureName}StoreFactory.Result
import com.arkivanov.mvikotlin.core.store.Reducer

internal class ${featureName}Reducer : Reducer<State, Result> {
    override fun State.reduce(result: Result): State {
        return when (result) {

        }
    }
}
