package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.util.ValidationUtil.setPasswordConfirmErrorMessage
import org.junit.Rule
import org.junit.Test

class PasswordConfirmInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    fun setPasswordConfirmInputField(
        password: String,
        passwordConfirm: String,
    ) {
        composeTestRule.setContent {
            PasswordConfirmInputField(
                value = passwordConfirm,
                errorMessage = setPasswordConfirmErrorMessage(password, passwordConfirm),
                onValueChange = {}
            )
        }
    }

    @Test
    fun `비밀번호_확인_입력값과_비밀번호_입력값은_같아야_한다`() {
        // given
        val password = "1234567890"
        val passwordConfirm = "1234567890"

        // when
        setPasswordConfirmInputField(password, passwordConfirm)

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
        setPasswordConfirmInputField(password, passwordConfirm)

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}
