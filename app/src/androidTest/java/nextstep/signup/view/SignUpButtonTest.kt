package nextstep.signup.view

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun beforeEach() {
        composeTestRule.setContent {
            SignUpScreen()
        }
    }

    @Test
    fun 회원가입_버튼이_활성화_된다() {
        // when
        composeTestRule.setContent {
            SignUpButton(enabled = true)
        }

        // then
        composeTestRule.onNodeWithText(SIGN_UP)
            .assertIsEnabled()
    }

    @Test
    fun 회원가입_버튼이_비활성화_된다() {
        // when
        composeTestRule.setContent {
            SignUpButton(enabled = false)
        }

        // then
        composeTestRule.onNodeWithText(SIGN_UP)
            .assertIsNotEnabled()
    }

    @Test
    fun 사용자_이름이_Valid_상태가_아니면_회원가입_버튼이_비활성화_된다() {
        // given
        beforeEach()

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("컴")
        composeTestRule.onNodeWithText(EMAIL).performTextInput("test@example.com")
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r4")

        // then
        composeTestRule.onNodeWithText(SIGN_UP)
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일이_Valid_상태가_아니면_회원가입_버튼이_비활성화_된다() {
        // given
        beforeEach()

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("컴포즈")
        composeTestRule.onNodeWithText(EMAIL).performTextInput("test@example")
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r4")

        // then
        composeTestRule.onNodeWithText(SIGN_UP)
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_Valid_상태가_아니면_회원가입_버튼이_비활성화_된다() {
        // given
        beforeEach()

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("컴포즈")
        composeTestRule.onNodeWithText(EMAIL).performTextInput("test@example.com")
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r4")

        // then
        composeTestRule.onNodeWithText(SIGN_UP)
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_일치하지_않으면_회원가입_버튼이_비활성화_된다() {
        // given
        beforeEach()

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("컴포즈")
        composeTestRule.onNodeWithText(EMAIL).performTextInput("test@example.com")
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r45")

        // then
        composeTestRule.onNodeWithText(SIGN_UP)
            .assertIsNotEnabled()
    }

    @Test
    fun 모든_상태가_유효하면_회원가입_버튼이_활성화_된다() {
        // given
        beforeEach()

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("컴포즈")
        composeTestRule.onNodeWithText(EMAIL).performTextInput("test@example.com")
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r4")

        // then
        composeTestRule.onNodeWithText(SIGN_UP)
            .assertIsEnabled()
    }

    companion object {
        private const val SIGN_UP = "Sign Up"
        private const val USERNAME = "Username"
        private const val EMAIL = "Email"
        private const val PASSWORD = "Password"
        private const val PASSWORD_CONFIRM = "Password Confirm"
    }
}