package nextstep.signup.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignUpScreen()
        }
    }

    @Test
    fun 비밀번호가_일치하지_않으면_에러메시지가_노출된다() {
        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r45")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_일치하면_에러메시지가_노출되지_않는다() {
        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r4")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD = "Password"
        private const val PASSWORD_CONFIRM = "Password Confirm"
        private const val PASSWORD_CONFIRM_ERROR = "비밀번호가 일치하지 않습니다."
    }
}