package nextstep.signup.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult
import nextstep.signup.ui.signup.component.PasswordTextField
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호를_입력하면_텍스트가_암호화된다() {
        // given:
        var password by mutableStateOf("")
        val passwordTextFieldTag = "Password"

        // when:
        composeTestRule.apply {
            setContent {
                PasswordTextField(
                    password = password,
                    onPasswordChanged = { password = it },
                    passwordValidation = SignupValidationResult.Success,
                )
            }
            onNodeWithContentDescription(passwordTextFieldTag).performTextInput("1234qwer")
        }

        // then:
        composeTestRule.onNodeWithContentDescription(passwordTextFieldTag)
            .assertTextContains("••••••••")
    }

    @Test
    fun 비밀번호_유효성_검증이_Failure_상태이면_에러문구가_출력된다() {
        // given:
        val passwordErrorTag = "PasswordTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                PasswordTextField(
                    password = "",
                    onPasswordChanged = {},
                    passwordValidation = SignupValidationResult.Failure(USERNAME_RULE_INVALIDATION),
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(passwordErrorTag).assertExists()
    }

    @Test
    fun 비밀번호_유효성_검증이_Success_상태이면_에러문구가_출력되지_않는다() {
        // given:
        val passwordErrorTag = "PasswordTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                PasswordTextField(
                    password = "",
                    onPasswordChanged = {},
                    passwordValidation = SignupValidationResult.Success,
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(passwordErrorTag).assertDoesNotExist()
    }
}
