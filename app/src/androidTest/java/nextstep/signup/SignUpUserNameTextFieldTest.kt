package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.signup.SignUpScreen
import org.junit.Rule
import org.junit.Test

class SignUpUserNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 유저이름이_2글자_미만일_때_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("일")

        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 유저이름이_5글자_초과일_때_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("일이삼사오육")

        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 유저이름이_숫자형식일_때_오류_메시지를_노출한다() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("12345")

        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }
}