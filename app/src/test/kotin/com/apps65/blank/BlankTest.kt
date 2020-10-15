package com.apps65.blank

import com.apps65.mvi.saving.SavedStateKeeperImpl
import com.apps65.mvi.store.UnicastStoreFactory
import com.apps65.mvitemplate.common.DispatchersProvider
import com.apps65.mvitemplate.domain.blank.store.BlankStore
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import com.apps65.mvitemplate.domain.blank.store.ExecutorsFactory
import com.apps65.netutils.connection.ConnectionService
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
            stateKeeper = SavedStateKeeperImpl(),
            executorsFactory = ExecutorsFactory(dispatcherProvider),
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
                val currentState = blankStore.state as BlankStore.State.Blank
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
