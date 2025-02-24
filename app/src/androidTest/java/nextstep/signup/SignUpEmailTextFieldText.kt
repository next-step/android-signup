package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.signup.SignUpScreen
import org.junit.Rule
import org.junit.Test

class SignUpEmailTextFieldText {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이메일에_도메인이_없으면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("email@.com")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 이메일에_최상위_도메인이_없으면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("email@domain")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 이메일에_골뱅이_기호가_없으면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("email.domain.com")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 이메일_앞부분이_없으면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("@domain.com")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 이메일이_올바르면_오류_메시지가_노출되지_않는다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("email@domain.com")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertDoesNotExist()
    }
}