package ${packageName}.domain.${featurePackage}.store

import android.os.Parcelable
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Intent
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Label
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.State
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.parcelize.Parcelize

interface ${featureName}Store : Store<Intent, State, Label> {
    sealed class Intent {
    }

    sealed class Action {

    }

    @Parcelize
    data class State(

    ) : Parcelable

    sealed class Label {

    }
}
