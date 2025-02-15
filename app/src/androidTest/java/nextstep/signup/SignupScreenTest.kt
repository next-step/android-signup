package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.screen.signup.SignupScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SignupScreenTest(
    private val usernameValidation: ValidationState,
    private val emailValidation: ValidationState,
    private val passwordValidation: ValidationState,
    private val passwordConfirmValidation: ValidationState,
    private val buttonEnabled: Boolean,
) {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val usernameValidationState = mutableStateOf<ValidationState>(ValidationState.Success)
    private val emailValidationState = mutableStateOf<ValidationState>(ValidationState.Success)
    private val passwordValidationState = mutableStateOf<ValidationState>(ValidationState.Success)
    private val passwordConfirmValidationState =
        mutableStateOf<ValidationState>(ValidationState.Success)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignupScreen(
                username = "",
                email = "",
                password = "",
                passwordConfirm = "",
                onUsernameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onPasswordConfirmChange = {},
                usernameValidationState = usernameValidationState.value,
                emailValidationState = emailValidationState.value,
                passwordValidationState = passwordValidationState.value,
                passwordConfirmValidationState = passwordConfirmValidationState.value,
                showCompleteSnackbar = {}
            )
        }
    }

    @Test
    fun 하나라도_결과가_실패이면_버튼이_활성화되지_않고_모두_성공일_때_활성화한다() {
        // when
        usernameValidationState.value = usernameValidation
        emailValidationState.value = emailValidation
        passwordValidationState.value = passwordValidation
        passwordConfirmValidationState.value = passwordConfirmValidation

        // then
        if (buttonEnabled) {
            composeTestRule
                .onNodeWithTag("SignupButton")
                .assertIsEnabled()
        } else {
            composeTestRule
                .onNodeWithTag("SignupButton")
                .assertIsNotEnabled()
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "username: {0}, email: {1}, password: {2}, confirm: {3}")
        fun data(): Collection<Array<Any>> {
            val success = ValidationState.Success
            val usernameError = ValidationState.Error(R.string.error_username_format)
            val emailError = ValidationState.Error(R.string.error_email_format)
            val passwordError = ValidationState.Error(R.string.error_password_length)
            val passwordConfirmError =
                ValidationState.Error(R.string.error_password_confirm_not_equal)

            return listOf(
                // 기대값 : 버튼 활성화
                arrayOf(success, success, success, success, true),
                // 기대값 : 버튼 비활성화
                arrayOf(usernameError, success, success, success, false),
                arrayOf(success, emailError, success, success, false),
                arrayOf(success, success, passwordError, success, false),
                arrayOf(success, success, success, passwordConfirmError, false),
                arrayOf(usernameError, emailError, passwordError, passwordConfirmError, false)
            )
        }
    }
}
