package com.apps65.blank

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.apps65.mvitemplate.presentation.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ExampleBlankScreenTest : TestCase() {
    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        run {
            step("Open Simple Screen") {
                activityTestRule.launchActivity(null)
                testLogger.i("I am testLogger")
                device.screenshots.take("Additional_screenshot")
                BlankScreen {
                    increment {
                        isVisible()
                    }
                }
            }

            step("Click button_1 and check counter") {
                BlankScreen {
                    increment {
                        click()
                    }
                    counter {
                        containsText("1")
                    }
                }
            }
        }
}
