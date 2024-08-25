package nextstep.signup.study

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.SignUpTextFieldComponent
import nextstep.signup.validation.InputValidation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val inputValidation = InputValidation.PasswordValidation(
        PASSWORD_VALID_ERROR,
        PASSWORD_LENGTH_ERROR
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextFieldComponent(
                labelText = "password",
                {  inputValidation.checkValidation(password.value) }
            )
        }
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // when
        password.value = "1234qwer"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        password.value = "123qwe"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()

    }

    @Test
    fun 비밀번호에_영문이_없으면_에러메시지가_노출된다() {
        // when
        password.value = "12345678"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_VALID_ERROR)
            .assertExists()

    }

    @Test
    fun 비밀번호에_숫자가없으면_에러메시지가_노출된다() {
        // when
        password.value = "qwertyuiop"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_VALID_ERROR)
            .assertExists()

    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_VALID_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}
