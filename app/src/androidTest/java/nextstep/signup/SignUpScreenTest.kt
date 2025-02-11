@file:Suppress("NonAsciiCharacters")

package nextstep.signup

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextInput
import nextstep.signup.signup.SignUpButton
import nextstep.signup.signup.SignUpScreen
import nextstep.signup.signup.SignUpTitle
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signup_title_test() {
        // given
        composeTestRule.setContent {
            SignUpTitle()
        }

        // then
        composeTestRule
            .onNodeWithText("Welcome to Compose \uD83D\uDE80")
            .assertExists()
    }

    @Test
    fun signup_button_test() {
        // given
        composeTestRule.setContent {
            SignUpButton()
        }

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertExists()
    }

    @Test
    fun signup_screen_test() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onRoot()
            .onChildren()
            .assertCountEquals(6)
            .onFirst()
            .assert(hasText("Welcome to Compose \uD83D\uDE80"))
    }

    @Test
    fun 유저이름이_2글자_미만일_때_오류_메시지_테스트() {
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
    fun 유저이름이_5글자_초과일_때_오류_메시지_테스트() {
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
    fun 유저이름이_숫자형식일_때_오류_메시지_테스트() {
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

    @Test
    fun 이메일_형식이_올바르지_않을_때_오류_메시지_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("test")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_8글자_미만일_때_오류_메시지_테스트() {
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
    fun 비밀번호가_16글자_초과일_때_오류_메시지_테스트() {
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
    fun 비밀번호가_영문과_숫자를_포함하지_않을_때_오류_메시지_테스트() {
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

    @Test
    fun 비밀번호와_비밀번호확인이_일치하지_않을_때_오류_메시지_테스트() {
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
}