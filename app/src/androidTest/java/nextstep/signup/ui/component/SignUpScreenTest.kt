package nextstep.signup.ui.component

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.signup.SignUpScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignUpScreen()
        }
    }

    @Test
    fun 모든_유효성_검사가_통과하면_회원가입_버튼이_활성화된다() {
        composeTestRule.onNodeWithTag("username")
            .performTextInput("dino")
        composeTestRule.onNodeWithTag("email")
            .performTextInput("dino@next.step")
        composeTestRule.onNodeWithTag("password")
            .performTextInput("password1234")
        composeTestRule.onNodeWithTag("passwordConfirm")
            .performTextInput("password1234")

        composeTestRule.onNodeWithTag("signUpButton")
            .assertExists()
            .assertIsEnabled()
    }

}
