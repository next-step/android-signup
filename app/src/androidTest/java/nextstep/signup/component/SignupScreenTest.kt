package nextstep.signup.component

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author : interworks_aos
 * @CreatedDate : 2025. 2. 10. 오후 5:05
 * @PackageName : nextstep.signup.component
 * @ClassName: SignupScreenTest
 * @Description:
 */
class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignupScreen(
                onClickSignUp = {}
            )
        }
    }

    @Test
    fun `모든입력이_유효하면_버튼이_활성화된다`() {
        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("컴포즈")

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("android@gmail.com")

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("1q2w3e4r")

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("1q2w3e4r")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }
}
