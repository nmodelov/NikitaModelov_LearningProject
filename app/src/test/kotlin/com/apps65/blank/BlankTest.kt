package com.apps65.blank

import android.os.Parcelable
import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvi.store.UnicastStoreFactory
import com.apps65.mvitemplate.domain.blank.store.BlankStore
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import com.apps65.mvitemplate.domain.blank.store.ExecutorsFactory
import com.apps65.mvitemplate.domain.blank.store.RollDiceExecutorDelegate
import com.apps65.netutils.connection.ConnectionService
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.isAssertOnMainThreadEnabled
import com.arkivanov.mvikotlin.rx.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
object BlankTest : Spek({
    isAssertOnMainThreadEnabled = false
    val dispatcher = TestCoroutineDispatcher()
    val dispatcherProvider = object : DispatchersProvider {
        override val default = dispatcher
        override val main = Dispatchers.Main
        override val unconfined = dispatcher
        override val io = dispatcher
    }

    beforeEachGroup {
        isAssertOnMainThreadEnabled = false
        Dispatchers.setMain(dispatcher)
    }

    describe("blank screen test") {
        val blankStoreFactory = BlankStoreFactory(
            storeFactory = UnicastStoreFactory,
            stateKeeper = object : SavedStateKeeper {
                override fun <P : Parcelable> get(key: String): P? = null

                override fun <S : Store<*, *, *>, P : Parcelable> register(
                    store: S,
                    key: String,
                    stateProvider: S.() -> P
                ) = Unit

                override fun unregister() = Unit
            },
            executorsFactory = ExecutorsFactory(
                dispatcherProvider,
                setOf(RollDiceExecutorDelegate(dispatcherProvider))
            ),
            connectionService = object : ConnectionService {
                override fun observeConnectionState(): Flow<Boolean> = flowOf(false)

                override fun hasConnection(): Boolean = false
            }
        )
        val blankStore = blankStoreFactory.create()

        it("should be 1 after increment") {
            runBlockingTest(dispatcher) {
                blankStore.accept(BlankStore.Intent.Increment)
                advanceUntilIdle() // <-- Если в проверяемом коде есть delay
                val currentState = blankStore.state
                assertThat(currentState.blankCount).isEqualTo(1)
            }
        }

        it("should publish Blank Label") {
            runBlockingTest(dispatcher) {
                var label: BlankStore.Label? = null

                blankStore.labels(
                    object : Observer<BlankStore.Label> {
                        override fun onComplete() = Unit

                        override fun onNext(value: BlankStore.Label) {
                            label = value
                        }
                    }
                )
                blankStore.accept(BlankStore.Intent.Increment)
                advanceUntilIdle()
                assertThat(label).isEqualTo(BlankStore.Label.Blank)
            }
        }
    }

    afterEachGroup {
        dispatcher.cleanupTestCoroutines()
        isAssertOnMainThreadEnabled = false
        Dispatchers.resetMain()
    }
})
