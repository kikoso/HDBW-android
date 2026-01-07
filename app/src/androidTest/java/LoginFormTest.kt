import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enrique.hdbwandroid.ui.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFormTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginForm_showsError_whenInputIsEmpty() {
       composeTestRule.setContent {
           LoginScreen()
       }

        composeTestRule.onNodeWithTag("inputField").performTextInput("Hello")

        composeTestRule.onNodeWithTag("inputField").performTextClearance()

        composeTestRule.onNodeWithText("Fehler: Eingabe darf nicht leer sein").assertExists()

    }
}
