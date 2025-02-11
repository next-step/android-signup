package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.textfield.PasswordError
import nextstep.signup.textfield.PasswordTextField
import nextstep.signup.textfield.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordError = mutableStateOf(PasswordError.NONE)

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                value = password.value,
                error = passwordError.value,
                onValueChange = { password.value = it })
        }
    }

    @Test
    fun 비밀번호가_빈문자열이_아니고_형식이_맞으면_에러메세지가_노출되지_않는다() {
        // when
        password.value = "qwer1234!@#$"
        passwordError.value = PasswordError.NONE

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(PASSWORD_REQUIRED_CHARACTER_NOT_INCLUDE_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_빈문자열이_아니고_오류가_LENGTH_ERROR면_길이_에러메세지가_노출된다() {
        // when
        password.value = "qwer1234!@#\$qwer1234!@#$"
        passwordError.value = PasswordError.LENGTH_ERROR

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_빈문자열이_아니고_오류가_REQUIRED_CHARACTER_NOT_INCLUDE_ERROR면_문자_에러메세지가_노출된다() {
        // when
        password.value = "qwer!@#$"
        passwordError.value = PasswordError.REQUIRED_CHARACTER_NOT_INCLUDE_ERROR

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_REQUIRED_CHARACTER_NOT_INCLUDE_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_빈문자열이면_에러메세지가_노출되지_않는다() {
        // when
        password.value = ""
        passwordError.value = PasswordError.LENGTH_ERROR

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(PASSWORD_REQUIRED_CHARACTER_NOT_INCLUDE_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_REQUIRED_CHARACTER_NOT_INCLUDE_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}