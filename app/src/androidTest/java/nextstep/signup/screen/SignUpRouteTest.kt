package nextstep.signup.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpRouteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpRoute()
        }
    }

    @Test
    fun 모든_입력값이_VALID면_SingUpButton은_활성화되고_클릭하면_스낵바가뜬다() {
        composeTestRule.onNodeWithTag("name").performTextInput("user")
        composeTestRule.onNodeWithTag("email").performTextInput("dlwlgns1240@gmail.com")
        composeTestRule.onNodeWithTag("password").performTextInput("qwer1234")
        composeTestRule.onNodeWithTag("password_confirm").performTextInput("qwer1234")

        composeTestRule
            .onNodeWithText("Sign up")
            .assertIsEnabled()
            .performClick()

        composeTestRule
            .onNodeWithText("회원가입이 완료되었습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 입력값이_하나라도_VALID가아니라면_SigunUpButton은_비활성화된다() {
        composeTestRule.onNodeWithTag("name").performTextInput("user")
        composeTestRule.onNodeWithTag("email").performTextInput("dlwlgns1240")
        composeTestRule.onNodeWithTag("password").performTextInput("qwer1234")
        composeTestRule.onNodeWithTag("password_confirm").performTextInput("qwer1234")

        composeTestRule
            .onNodeWithText("Sign up")
            .assertIsNotEnabled()
    }
}
