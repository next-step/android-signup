package nextstep.signup.study.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.component.textField.EmailTextField
import nextstep.signup.model.EmailState
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이메일_형식이_올바르면_에러메시지가_노출되지_않는다() {
        composeTestRule.setContent {
            EmailTextField(
                email = "test@test.com", onEmailChange = {}
            )
        }
        composeTestRule.onNodeWithText("이메일 형식이 올바르지 않습니다.").assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        composeTestRule.setContent {
            EmailTextField(
                email = "invalid-email", onEmailChange = {}
            )
        }

        composeTestRule.onNodeWithText("이메일 형식이 올바르지 않습니다.").assertExists()
    }
}