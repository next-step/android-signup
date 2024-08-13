package nextstep.signup.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignUpScreen()
        }
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {

        // when
        composeTestRule.onNodeWithText(EMAIL).performTextInput("hello")

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르게_입력되어야_한다() {

        // when
        composeTestRule.onNodeWithText(EMAIL).performTextInput("hello@google.com")

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val EMAIL = "Email"
        private const val EMAIL_INVALID_ERROR = "이메일 형식이 올바르지 않습니다."

    }
}