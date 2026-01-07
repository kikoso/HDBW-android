package com.enrique.hdbwandroid

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enrique.hdbwandroid.ui.MyViewModel
import com.enrique.hdbwandroid.ui.WelcomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun welcomeScreen_updates_whenViewModelStateChanges() {
        val viewModel = MyViewModel()

        composeTestRule.setContent {
            WelcomeScreen(viewModel)
        }

        composeTestRule.onNodeWithText("Hallo, Enrique").assertExists()

        composeTestRule.runOnUiThread {
            viewModel.name = "Maria"
        }

        composeTestRule.onNodeWithText("Hallo, Maria").assertExists()

    }
}
