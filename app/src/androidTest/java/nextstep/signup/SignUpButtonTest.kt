package nextstep.signup

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.signup.SignUpButton
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun SignUpButton의_isEnabled매개변수가_true면_버튼이_활성화된다() {
        // given
        composeTestRule.setContent {
            SignUpButton(true)
        }

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun SignUpButton의_isEnabled매개변수가_false면_버튼이_비활성화된다() {
        // given
        composeTestRule.setContent {
            SignUpButton(false)
        }

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}