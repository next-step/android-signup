package nextstep.signup.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.signup.SignupInvalidationState.EMAIL_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult
import nextstep.signup.ui.signup.component.EmailTextField
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이메일을_입력하면_텍스트가_출력된다() {
        // given:
        var email by mutableStateOf("")
        val emailTextFieldTag = "Email"

        // when:
        composeTestRule.apply {
            setContent {
                EmailTextField(
                    email = email,
                    onEmailChanged = { email = it },
                    emailValidation = SignupValidationResult.Success,
                )
            }
            onNodeWithContentDescription(emailTextFieldTag).performTextInput("s9hn@github.com")
        }

        // then:
        composeTestRule.onNodeWithContentDescription(emailTextFieldTag)
            .assertTextContains("s9hn@github.com")
    }

    @Test
    fun 이메일_유효성_검증이_Failure_상태이면_에러문구가_출력된다() {
        // given:
        val emailErrorTag = "EmailTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                EmailTextField(
                    email = "",
                    onEmailChanged = {},
                    emailValidation = SignupValidationResult.Failure(EMAIL_RULE_INVALIDATION),
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(emailErrorTag).assertExists()
    }

    @Test
    fun 이메일_유효성_검증이_Success_상태이면_에러문구가_출력되지_않는다() {
        // given:
        val emailErrorTag = "EmailTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                EmailTextField(
                    email = "",
                    onEmailChanged = {},
                    emailValidation = SignupValidationResult.Success,
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(emailErrorTag).assertDoesNotExist()
    }
}
