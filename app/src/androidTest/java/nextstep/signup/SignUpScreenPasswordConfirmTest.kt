package nextstep.signup

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import org.junit.Rule
import org.junit.Test

class SignUpScreenPasswordConfirmTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호_확인을_입력해도_입력된_비밀번호_확인이_보이지_않는다() {
        // given
        val passwordInput = "a12345678!"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        // Password Confirm 필드에 입력
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(passwordInput)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_다르면_유효성_검사_실패_메세지가_보인다() {
        // given
        val invalidPasswordConfirmMessage = "비밀번호가 일치하지 않습니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("ABCD1234")
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("abcd1234!")

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordConfirmMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_같으면_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val invalidPasswordConfirmMessage = "비밀번호가 일치하지 않습니다."
        val passwordConfirm = "abcd1234"

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordConfirm)
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput(passwordConfirm)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordConfirmMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인_입력_후_비밀번호가_바뀌면_실패_메세지가_보인다() {
        val invalidPasswordConfirmMessage = "비밀번호가 일치하지 않습니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("123123123")

        // when
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("123123123")

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextReplacement("123123123a")

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordConfirmMessage)
            .assertExists()
    }
}
