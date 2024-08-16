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
import nextstep.signup.ui.signup.component.PasswordConfirmTextField
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호확인을_입력하면_텍스트가_암호화된다() {
        // given:
        var passwordConfirm by mutableStateOf("")
        val passwordConfirmTextFieldTag = "Password Confirm"

        // when:
        composeTestRule.apply {
            setContent {
                PasswordConfirmTextField(
                    passwordConfirm = passwordConfirm,
                    onPasswordConfirmChanged = { passwordConfirm = it },
                    passwordConfirmValidation = SignupValidationResult.Success,
                )
            }
            onNodeWithContentDescription(passwordConfirmTextFieldTag).performTextInput("1234qwer")
        }

        // then:
        composeTestRule.onNodeWithContentDescription(passwordConfirmTextFieldTag)
            .assertTextContains("••••••••")
    }

    @Test
    fun 비밀번호확인_유효성_검증이_Failure_상태이면_에러문구가_출력된다() {
        // given:
        val passwordConfirmErrorTag = "PasswordConfirmTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                PasswordConfirmTextField(
                    passwordConfirm = "",
                    onPasswordConfirmChanged = {},
                    passwordConfirmValidation = SignupValidationResult.Failure(
                        USERNAME_RULE_INVALIDATION
                    ),
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(passwordConfirmErrorTag).assertExists()
    }

    @Test
    fun 비밀번호확인_유효성_검증이_Success_상태이면_에러문구가_출력되지_않는다() {
        // given:
        val passwordConfirmErrorTag = "PasswordConfirmTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                PasswordConfirmTextField(
                    passwordConfirm = "",
                    onPasswordConfirmChanged = {},
                    passwordConfirmValidation = SignupValidationResult.Success
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(passwordConfirmErrorTag).assertDoesNotExist()
    }
}
