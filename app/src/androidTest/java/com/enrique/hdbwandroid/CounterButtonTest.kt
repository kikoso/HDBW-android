package com.enrique.hdbwandroid

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.enrique.hdbwandroid.ui.CounterButton
import org.junit.Rule
import org.junit.Test

class CounterButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counterButton_click_updatesText() {
        composeTestRule.setContent {
            CounterButton()
        }

        composeTestRule.onNodeWithText("Count: 0").assertIsDisplayed()
        composeTestRule.onNodeWithText("Count: 1").assertIsNotDisplayed()

        composeTestRule.onNodeWithText("Count: 0").performClick()

        composeTestRule.onNodeWithText("Count: 0").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("Count: 1").assertIsDisplayed()
    }
}
