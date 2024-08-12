package nextstep.signup.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.core.validation.EmailValidationResult
import nextstep.signup.core.validation.NameValidationResult
import nextstep.signup.core.validation.PasswordMatchValidationResult
import nextstep.signup.core.validation.PasswordValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val userNameValidationResult = mutableStateOf(NameValidationResult.VALID)
    private val emailValidationResult = mutableStateOf(EmailValidationResult.VALID)
    private val passwordValidationResult = mutableStateOf(PasswordValidationResult.VALID)
    private val passwordMatchValidationResult = mutableStateOf(PasswordMatchValidationResult.VALID)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpScreen(
                userName = "userName",
                email = "email",
                password = "password",
                passwordConfirm = "passwordConfirm",
                userNameValidationResult = userNameValidationResult.value,
                emailValidationResult = emailValidationResult.value,
                passwordValidationResult = passwordValidationResult.value,
                passwordMatchValidationResult = passwordMatchValidationResult.value,
                onUserNameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onPasswordConfirmChange = {},
                onSignUpClick = {},
                isSignUpEnabled = userNameValidationResult.value == NameValidationResult.VALID && emailValidationResult.value == EmailValidationResult.VALID &&
                        passwordValidationResult.value == PasswordValidationResult.VALID && passwordMatchValidationResult.value == PasswordMatchValidationResult.VALID,
            )
        }
    }

    @Test
    fun 모든_입력값이_VALID면_SingUpButton은_활성화된다() {
        userNameValidationResult.value = NameValidationResult.VALID
        emailValidationResult.value = EmailValidationResult.VALID
        passwordValidationResult.value = PasswordValidationResult.VALID
        passwordMatchValidationResult.value = PasswordMatchValidationResult.VALID

        composeTestRule
            .onNodeWithText("Sign up")
            .assertIsEnabled()
    }

    @Test
    fun 입력값이_하나라도_VALID가아니라면_SigunUpButton은_비활성화된다() {
        userNameValidationResult.value = NameValidationResult.VALID
        emailValidationResult.value = EmailValidationResult.INVALID_FORMAT
        passwordValidationResult.value = PasswordValidationResult.VALID
        passwordMatchValidationResult.value = PasswordMatchValidationResult.VALID

        composeTestRule
            .onNodeWithText("Sign up")
            .assertIsNotEnabled()
    }
}
