package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.util.ValidationUtil.PASSWORD_CONFIRM_NOT_SAME
import nextstep.signup.util.ValidationUtil.setPasswordConfirmErrorMessage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmInputField(
                value = passwordConfirm.value,
                errorMessage = setPasswordConfirmErrorMessage(password.value, passwordConfirm.value),
                onValueChange = { passwordConfirm.value = it }
            )
        }
    }

    @Test
    fun `비밀번호_확인_입력값과_비밀번호_입력값은_같아야_한다`() {
        // when
        password.value = "1234567890"
        passwordConfirm.value = "1234567890"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_NOT_SAME)
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호_확인_입력값과_비밀번호_입력값이_다르면_에러메세지가_노출된다`() {
        // when
        password.value = "1234567890"
        passwordConfirm.value = "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊ"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_NOT_SAME)
            .assertExists()
    }
}
