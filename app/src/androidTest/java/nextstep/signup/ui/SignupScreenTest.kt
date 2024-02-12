package nextstep.signup.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.signup.domain.EmailValidationResult
import nextstep.signup.domain.PasswordConfirmValidationResult
import nextstep.signup.domain.PasswordValidationResult
import nextstep.signup.domain.UsernameValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val usernameValidationResult = mutableStateOf(UsernameValidationResult.SUCCESS)
    private val emailValidationResult = mutableStateOf(EmailValidationResult.SUCCESS)
    private val passwordValidationResult = mutableStateOf(PasswordValidationResult.SUCCESS)
    private val confirmPasswordValidationResult =
        mutableStateOf(PasswordConfirmValidationResult.SUCCESS)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignupScreen(
                username = "",
                usernameValidationResult = usernameValidationResult.value,
                onUsernameChange = { },
                email = "",
                emailValidationResult = emailValidationResult.value,
                onEmailChange = { },
                password = "",
                passwordValidationResult = passwordValidationResult.value,
                onPasswordChange = { },
                passwordConfirm = "",
                passwordConfirmValidationResult = confirmPasswordValidationResult.value,
                onPasswordConfirmChange = { },
                onButtonClick = { },
            )
        }
    }

    @Test
    fun 모든_입력값이_유효하면_버튼이_활성화된다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.SUCCESS
        emailValidationResult.value = EmailValidationResult.SUCCESS
        passwordValidationResult.value = PasswordValidationResult.SUCCESS
        confirmPasswordValidationResult.value = PasswordConfirmValidationResult.SUCCESS

        // when & then
        composeTestRule
            .onNodeWithTag("button")
            .assertIsEnabled()
    }

    @Test
    fun 입력값이_하나라도_유효하지_않으면_버튼이_비활성화된다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.INVALID_FORMAT
        emailValidationResult.value = EmailValidationResult.SUCCESS
        passwordValidationResult.value = PasswordValidationResult.SUCCESS
        confirmPasswordValidationResult.value = PasswordConfirmValidationResult.SUCCESS

        // when & then
        composeTestRule
            .onNodeWithTag("button")
            .assertIsNotEnabled()
    }
}
