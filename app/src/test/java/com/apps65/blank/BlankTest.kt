package com.apps65.blank

import com.apps65.mvi.saving.SavedStateKeeperImpl
import com.apps65.mvitemplate.domain.blank.store.BlankStore
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import com.arkivanov.mvikotlin.core.utils.isAssertOnMainThreadEnabled
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.rx.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BlankTest : Spek({
    beforeEachGroup {
        isAssertOnMainThreadEnabled = false
        Dispatchers.setMain(newSingleThreadContext("Test UI thread"))
    }

    describe("blank screen test") {
        val blankStoreFactory = BlankStoreFactory(DefaultStoreFactory, SavedStateKeeperImpl())
        val blankStore by memoized { blankStoreFactory.create() }

        it("should be 1 after increment") {
            blankStore.accept(BlankStore.Intent.Blank)
            runBlocking(Dispatchers.Main) {
                val currentState = blankStore.state as BlankStore.State.Blank
                assertThat(currentState.blankCount).isEqualTo(1)
            }
        }

        it("should publish Blank Label") {
            var label: BlankStore.Label? = null

            blankStore.labels(object : Observer<BlankStore.Label> {
                override fun onComplete() = Unit

                override fun onNext(value: BlankStore.Label) {
                    label = value
                }
            })
            blankStore.accept(BlankStore.Intent.Blank)
            runBlocking(Dispatchers.Main) {
                assertThat(label).isEqualTo(BlankStore.Label.Blank)
            }
        }
    }

    afterEachGroup {
        isAssertOnMainThreadEnabled = true
        Dispatchers.resetMain()
    }
})
