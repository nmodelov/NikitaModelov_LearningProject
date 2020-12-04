package com.apps65.mvitemplate.domain.blank.store

import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvi.common.SuspendIntentExecutor
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Intent
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Label
import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@Suppress("MagicNumber")
internal class RollDiceExecutorDelegate @Inject constructor(
    private val dispatchersProvider: DispatchersProvider
) : BlankExecutorDelegate {
    override suspend fun executeIntent(intent: Intent, executor: SuspendIntentExecutor<Intent, State, Result, Label>) {
        withContext(dispatchersProvider.default) {
            if (intent !is Intent.RollDice) {
                return@withContext
            }
            executor.dispatch(Result.DiceResult(BlankStore.DiceState.Spinning))
            val delay = Random.nextLong(1_000, 3_000)
            delay(delay)
            val result = Random.nextInt(1, 6)
            executor.dispatch(Result.DiceResult(BlankStore.DiceState.DiceIdle(result)))
        }
    }
}
