package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordValidationResult = mutableStateOf(PasswordValidationResult.Valid)

    private val passwordConfirm = mutableStateOf("")
    private val confirmPasswordValidationResult = mutableStateOf(ConfirmPasswordValidationResult.Valid)

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = password.value,
                onPasswordChange = { password.value = it },
                validationResult = passwordValidationResult.value
            )

            PasswordConfirmTextField(
                confirmPassword = passwordConfirm.value,
                onPasswordChange = { passwordConfirm.value = it },
                validationResult = confirmPasswordValidationResult.value
            )
        }
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // given
        val passwordList = listOf("123456789", "12345678910")

        passwordList.forEach {
            // when
            password.value = it
            passwordValidationResult.value = PasswordValidationResult.Valid

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_LENGTH_ERROR)
                .assertDoesNotExist()
        }
    }

    @Test
    fun 비밀번호는_8에서_16자가_아니면_에러메시지가_노출된다() {
        // given
        val inputPassword = "1234567"

        // when
        password.value = inputPassword
        passwordValidationResult.value = PasswordValidationResult.InvalidSize

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문이나_숫자가_포함되지_않으면_에러메시지가_노출된다() {
        // given
        val passwordList = listOf("123456789", "abcdefghi")

        passwordList.forEach {

            // when
            password.value = it
            passwordValidationResult.value = PasswordValidationResult.InvalidFormat

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_FORMAT_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 비밀번호가_일치하지_않으면_에러메시지가_노출된다() {
        // given
        val inputPassword = "12345678ab"
        val inputPasswordConfirm = "12345678ba"

        // when
        password.value = inputPassword
        passwordConfirm.value = inputPasswordConfirm
        confirmPasswordValidationResult.value = ConfirmPasswordValidationResult.Mismatch

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_MISMATCH_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
