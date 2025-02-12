package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.signin.SignupScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignupScreen()
        }
    }

    @Test
    fun 이메일_형식이_올바르면_에러메시지_노출되지_않는다() {
        // given
        val text = "compose@gmail.com"

        // when
        composeTestRule
            .onNodeWithText(EMAIL_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지_노출된다() {
        // given
        val text = "compose#gmail.com"

        // when
        composeTestRule
            .onNodeWithText(EMAIL_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        // Label
        private const val EMAIL_LABEL = "Email"

        // ErrorMessage
        private const val EMAIL_INVALID_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}