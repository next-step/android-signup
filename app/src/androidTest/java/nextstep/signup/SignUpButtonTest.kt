package nextstep.signup

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 입력폼을_모두_올바르게_입력한_경우에만_버튼이_활성화된다() {
        // given
        val username = "최성훈"
        val email = "ver@test.com"
        val passwordInput = "abcd1234"
        val passwordConfirm = "abcd1234"

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
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

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun 유효성_검사에_실패하면_버튼은_비활성화된다() {
        // given
        val invalidUsername = "최" // 한 글자 이름
        val email = "ver@test.com"
        val passwordInput = "abcd1234"
        val passwordConfirm = "abcd1234"

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule.onNodeWithText("Username")
            .performTextInput(invalidUsername)
        composeTestRule.onNodeWithText("Email")
            .performTextInput(email)
        composeTestRule.onNodeWithText("Password")
            .performTextInput(passwordInput)
        composeTestRule.onNodeWithText("Password Confirm")
            .performTextInput(passwordConfirm)

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 입력폼이_완전히_입력되지_않은_경우_버튼은_비활성화된다() {
        // given
        val username = "최성훈"
        val email = "ver@test.com"
        val passwordInput = "abcd1234"

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule.onNodeWithText("Username")
            .performTextInput(username)
        composeTestRule.onNodeWithText("Email")
            .performTextInput(email)
        composeTestRule.onNodeWithText("Password")
            .performTextInput(passwordInput)
        // "Password Confirm" 필드는 입력하지 않음

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}