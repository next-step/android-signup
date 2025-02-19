package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.signup.SignUpScreen
import org.junit.Rule
import org.junit.Test

class SignUpPasswordConfirmTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호와_비밀번호확인이_일치하지_않으면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("12345678")

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("123456789")

        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호확인이_일치하지_하면_오류_메시지를_노출하지_않는다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("a12345678")

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("a12345678")

        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertDoesNotExist()
    }
}