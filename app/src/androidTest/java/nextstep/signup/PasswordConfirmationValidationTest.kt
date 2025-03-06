package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.ui.component.PasswordConfirmationTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmationValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val confirmPassword = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmationTextField(
                value = password.value,
                onValueChange = {},
                label = "",
                errorMessage = InputValidation.validateConfirmPassword(
                    password = password.value,
                    confirmPassword = confirmPassword.value
                )?.let { stringResource(it) },
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }

    @Test
    fun 비밀번호와_비밀번호_확인에_입력한_값이_동일해야_한다() {
        password.value = "compose12"
        confirmPassword.value = "compose12"

        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인에_입력한_값이_다르면_에러메시지가_노출된다() {
        password.value = "compose12"
        confirmPassword.value = "compase12"

        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_MISMATCH_ERROR = "비밀번호가 일치하지 않습니다."
    }
}