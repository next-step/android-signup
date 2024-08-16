package nextstep.signup.signup

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import nextstep.signup.ui.signup.SignUpButton
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 회원가입_버튼이_disabled_이면_클릭되지_않는다() {
        // given
        composeTestRule.setContent {
            SignUpButton(
                isEnabled = false,
                onSignUpClick = { }
            )
        }

        // when
        val signUpButton = composeTestRule
            .onNodeWithTag("회원가입 버튼")
            .performClick()

        // then
        signUpButton.assertIsNotEnabled()
    }

    @Test
    fun 회원가입_버튼이_enabled_이면_클릭된다() {
        // given
        composeTestRule.setContent {
            SignUpButton(
                isEnabled = true,
                onSignUpClick = { }
            )
        }

        // when
        val signUpButton = composeTestRule
            .onNodeWithTag("회원가입 버튼")
            .performClick()

        // then
        signUpButton.assertIsEnabled()
    }
}