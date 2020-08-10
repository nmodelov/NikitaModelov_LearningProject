package com.apps65

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ExampleUnitTest : Spek({
    describe("simple addition test") {
        it("should be 4") {
            assertThat(2 + 2).isEqualTo(4)
        }
    }
})

