package nextstep.signup

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignUpScreenPasswordTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호를_입력해도_입력된_비밀번호가_보이지_않는다() {
        // given
        val passwordInput = "a12345678!"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(passwordInput)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_길이가_8미만이면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val passwordInput = "Abc12!" // 길이 6 (< 8)
        val invalidPasswordLengthMessage = "비밀번호는 8~16자여야 합니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLengthMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호_길이가_16초과이면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val passwordInput = "Abc12345Abc12345X" // 길이 17 (> 16)
        val invalidPasswordLengthMessage = "비밀번호는 8~16자여야 합니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLengthMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호에_숫자가_없으면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val passwordInput = "Abcdefgh" // 숫자 없음
        val invalidPasswordLetterMessage = "비밀번호는 영문과 숫자를 포함해야 합니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLetterMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호에_영문이_없으면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val passwordInput = "12345678" // 영문 없음
        val invalidPasswordLetterMessage = "비밀번호는 영문과 숫자를 포함해야 합니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLetterMessage)
            .assertExists()
    }

    @Test
    fun 올바른_비밀번호_입력시_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val passwordInput = "Abcd1234"
        val invalidPasswordLengthMessage = "비밀번호는 8~16자여야 합니다."
        val invalidPasswordLetterMessage = "비밀번호는 영문과 숫자를 포함해야 합니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLengthMessage)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(invalidPasswordLetterMessage)
            .assertDoesNotExist()
    }
}
