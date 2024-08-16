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
import nextstep.signup.ui.signup.component.UsernameTextField
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 유저이름을_입력하면_텍스트가_출력된다() {
        // given:
        var username by mutableStateOf("")
        val usernameTextFieldTag = "Username"

        // when:
        composeTestRule.apply {
            setContent {
                UsernameTextField(
                    username = username,
                    onUsernameChanged = { username = it },
                    usernameValidation = SignupValidationResult.Success,
                )
            }
            onNodeWithContentDescription(usernameTextFieldTag).performTextInput("s9hn")
        }

        // then:
        composeTestRule.onNodeWithContentDescription(usernameTextFieldTag)
            .assertTextContains("s9hn")
    }

    @Test
    fun 유저이름_유효성_검증이_Failure_상태이면_에러문구가_출력된다() {
        // given:
        val usernameErrorTag = "UsernameTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                UsernameTextField(
                    username = "",
                    onUsernameChanged = {},
                    usernameValidation = SignupValidationResult.Failure(USERNAME_RULE_INVALIDATION),
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(usernameErrorTag).assertExists()
    }

    @Test
    fun 유저이름_유효성_검증이_Success_상태이면_에러문구가_출력되지_않는다() {
        // given:
        val usernameErrorTag = "UsernameTextFieldError"

        // when:
        composeTestRule.apply {
            setContent {
                UsernameTextField(
                    username = "",
                    onUsernameChanged = {},
                    usernameValidation = SignupValidationResult.Success,
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(usernameErrorTag).assertDoesNotExist()
    }
}
