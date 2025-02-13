package nextstep.signup.ui.component

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignupButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // given
        composeTestRule.setContent {
            SignupScreen()
        }
    }

    @Test
    fun 모든_입력폼이_유효하지_않으면_가입_버튼이_비활성화_상태가_된다() {
        // when
        val username = "name"
        val email = "user@domain.com"
        val passwordInput = "password1"
        val passwordConfirm = "password2"

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
            .onNodeWithText("Sign up")
            .assertIsNotEnabled()
    }

    @Test
    fun 모든_입력폼을_유효하게_입력하면_가입_버튼이_활성화_된다() {
        // when
        val username = "name"
        val email = "user@domain.com"
        val passwordInput = "password1"
        val passwordConfirm = "password1"

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
            .onNodeWithText("Sign up")
            .assertIsEnabled()
    }
}