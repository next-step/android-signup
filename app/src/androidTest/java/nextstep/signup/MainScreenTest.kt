package nextstep.signup

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `회원가입_폼에_정보를_입력하고_회원가입_버튼을_클릭하면_스낵바를_띄운다`() {
        // given
        val username = "최성훈"
        val email = "ver@test.com"
        val passwordInput = "abcd1234"
        val passwordConfirm = "abcd1234"

        composeTestRule.setContent {
            MaterialTheme {
                MainScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(email)

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput(passwordConfirm)

        composeTestRule
            .onNodeWithText("Sign Up")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("회원가입 완료", useUnmergedTree = true)
            .assertIsDisplayed()
    }
}
