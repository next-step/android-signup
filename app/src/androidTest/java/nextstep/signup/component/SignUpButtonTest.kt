package nextstep.signup.component

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.signup.util.validation.ValidationResult
import nextstep.signup.util.validation.Validator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var username by mutableStateOf("")
    private val userNameValidationResult by derivedStateOf { Validator.userNameValidate(username) }
    private var email by mutableStateOf("")
    private val emailValidationResult by derivedStateOf { Validator.emailValidate(email) }
    private var password by mutableStateOf("")
    private val passwordValidationResult by derivedStateOf { Validator.passwordValidate(password) }
    private var passwordConfirm by mutableStateOf("")
    private val passwordConfirmValidationResult by
    derivedStateOf { Validator.passwordConfirmValidate(password, passwordConfirm) }

    private val enabledSignUpButton by derivedStateOf {
        userNameValidationResult is ValidationResult.Success &&
                emailValidationResult is ValidationResult.Success &&
                passwordValidationResult is ValidationResult.Success &&
                passwordConfirmValidationResult is ValidationResult.Success
    }

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpButton(
                modifier = Modifier.testTag("SignUpButton"),
                onClickButton = {},
                enabledSignUpButton = enabledSignUpButton
            )
        }
    }

    @Test
    fun `이름과_이메일과_비밀번호와_비밀번호_확인이_모두_유효하다면_Sign_Up_버튼은_활성화_상태이다`() {
        username = "김컴포즈"
        email = "kim@compose.com"
        password = "1234asdf"
        passwordConfirm = "1234asdf"

        composeTestRule
            .onNodeWithTag("SignUpButton")
            .assertIsEnabled()
    }

    @Test
    fun `이름과_이메일과_비밀번호와_비밀번호_확인_중_하나라도_유효하지_않다면_Sign_Up_버튼은_비활성화_상태이다`() {
        username = "김컴포즈"
        email = "kim@composecom"
        password = "1234asdf"
        passwordConfirm = "1234asdf"

        composeTestRule
            .onNodeWithTag("SignUpButton")
            .assertIsNotEnabled()
    }
}