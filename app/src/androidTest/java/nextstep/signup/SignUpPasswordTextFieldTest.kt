package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.signup.SignUpScreen
import org.junit.Rule
import org.junit.Test

class SignUpPasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호가_8글자_미만이면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("1234567")

        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_16글자를_초과하면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("12345678901234567")

        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("123456789")

        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }
}