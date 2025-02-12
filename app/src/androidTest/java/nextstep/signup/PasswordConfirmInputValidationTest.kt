package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.userregister.widget.PasswordConfirmInputField
import org.junit.Rule
import org.junit.Test

class PasswordConfirmInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `비밀번호_확인_입력값과_비밀번호_입력값은_같아야_한다`() {
        // given
        val password = "1234567890"
        val passwordConfirm = "1234567890"

        // when
        composeTestRule.setContent {
            PasswordConfirmInputField(
                password = password,
                passwordConfirm = passwordConfirm,
                onValueChange = {}
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호_확인_입력값과_비밀번호_입력값이_다르면_에러메세지가_노출된다`() {
        // given
        val password = "1234567890"
        val passwordConfirm = "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊ"

        // when
        composeTestRule.setContent {
            PasswordConfirmInputField(
                password = password,
                passwordConfirm = passwordConfirm,
                onValueChange = {}
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}
