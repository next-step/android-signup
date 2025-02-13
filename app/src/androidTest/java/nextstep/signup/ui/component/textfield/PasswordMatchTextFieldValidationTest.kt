package nextstep.signup.ui.component.textfield

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.util.PasswordMatchValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordMatchTextFieldValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val enteredPassword = "password1"

    private val passwordMatch = mutableStateOf("")
    private val passwordMatchValidator = PasswordMatchValidator(
        getEnteredPassword = { enteredPassword }
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                inputValue = passwordMatch.value,
                onInputChange = { passwordMatch.value = it },
                validResult = passwordMatchValidator.validate(passwordMatch.value),
            )
        }
    }

    @Test
    fun 주어진_비밀번호와_동일한_비밀번호_입력시_에러메시지가_노출되지_않는다() {
        passwordMatch.value = "password1"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 주어진_비밀번호와_다른_비밀번호_입력시_에러메시지가_노출된다() {
        passwordMatch.value = "password2"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_MISMATCH_ERROR = "비밀번호가 일치하지 않습니다."
    }
}