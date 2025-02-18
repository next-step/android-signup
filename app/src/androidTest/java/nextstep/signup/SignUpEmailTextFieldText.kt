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
    fun 이메일_형식이_올바르지_않으면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("test")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }
}