package nextstep.signup.ui.component.textfield

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.util.PasswordValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                inputValue = password.value,
                onInputChange = { password.value = it },
                validResult = PasswordValidator.validate(password.value),
            )
        }
    }

    @Test
    fun 비밀번호는_8에서_16자가_아니면_에러메시지가_노출된다() {
        val invalidPasswords = listOf(
            "pass",
            "passwordpasswordp",
        )

        invalidPasswords.forEach { invalidPassword ->
            // when
            password.value = invalidPassword

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_LENGTH_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 비밀번호에_영문과_숫자가_포함되어_있지_않으면_에러메시지가_노출된다() {
        val invalidPasswords = listOf(
            "password",
            "12345678",
        )

        invalidPasswords.forEach { invalidPassword ->
            // when
            password.value = invalidPassword

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_INVALID_FORMAT_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 조건에_맞는_비밀번호가_입력된_경우_에러메시지가_노출되지_않는다() {
        // when
        password.value = "password1"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_INVALID_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}