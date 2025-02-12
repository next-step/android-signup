package nextstep.signup

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignUpScreenEmailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이메일을_입력하면_입력된_이메일이_보인다() {
        // given
        val emailInput = "ver@test.com"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(emailInput)

        // then
        composeTestRule
            .onNodeWithText(emailInput)
            .assertExists()
    }

    @Test
    fun 잘못된_이메일_입력시_유효성_검사_실패_메세지가_보인다() {
        // given
        val emailInput = "invalidEmail" // 잘못된 이메일 형식
        val invalidEmailMessage = "이메일 형식이 올바르지 않습니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(emailInput)

        // then
        composeTestRule
            .onNodeWithText(invalidEmailMessage)
            .assertExists()
    }

    @Test
    fun 올바른_이메일_입력시_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val emailInput = "valid@test.com" // 올바른 이메일 형식
        val invalidEmailMessage = "이메일 형식이 올바르지 않습니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(emailInput)

        // then
        composeTestRule
            .onNodeWithText(invalidEmailMessage)
            .assertDoesNotExist()
    }
}
