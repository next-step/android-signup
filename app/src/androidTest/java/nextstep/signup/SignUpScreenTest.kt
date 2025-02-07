package nextstep.signup

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이름을_입력하면_입력된_이름이_보인다() {
        // given
        val userName = "ChoiSeongHoon"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(userName)

        composeTestRule
            .onNodeWithText(userName)
            .assertExists()
    }

    @Test
    fun 이메일을_입력하면_입력된_이름이_보인다() {
        // given
        val emailInput = "ver@test.com"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(emailInput)

        composeTestRule
            .onNodeWithText(emailInput)
            .assertExists()
    }

    @Test
    fun 비밀번호를_입력해도_입력된_비밀번호가_보이지_않는다() {
        // given
        val passwordInput = "12345678!"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        composeTestRule
            .onNodeWithText(passwordInput)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인을_입력해도_입력된_비밀번호_확인이_보이지_않는다() {
        // given
        val passwordInput = "12345678!"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        composeTestRule
            .onNodeWithText(passwordInput)
            .assertDoesNotExist()
    }
}