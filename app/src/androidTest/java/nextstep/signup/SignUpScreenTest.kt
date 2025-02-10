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
    fun 유저이름_글자수_미달_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.USERNAME.hint)
            .performTextInput("일")

        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 유저이름_글자수_초과_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.USERNAME.hint)
            .performTextInput("일이삼사오육칠")

        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 유저이름_숫자_입력_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.USERNAME.hint)
            .performTextInput("123456")

        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }

    @Test
    fun 이메일_형식_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.EMAIL.hint)
            .performTextInput("test")

        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호_글자수_미달_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.PASSWORD.hint)
            .performTextInput("1234567")

        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호_글자수_초과_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.PASSWORD.hint)
            .performTextInput("12345678901234567")

        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호_영문_숫자_포함_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.PASSWORD.hint)
            .performTextInput("123456789")

        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호_확인_불일치_오류_테스트() {
        // given
        composeTestRule.setContent {
            SignUpScreen()
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.PASSWORD.hint)
            .performTextInput("12345678")

        composeTestRule
            .onNodeWithText(SignUpTextFieldType.PASSWORD_CONFIRM.hint)
            .performTextInput("123456789")

        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}