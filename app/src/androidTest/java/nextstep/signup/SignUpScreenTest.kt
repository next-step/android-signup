package nextstep.signup

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
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
    fun signup_text_field_test() {
        // given
        composeTestRule.setContent {
            SignUpTextField(SignUpTextFieldType.USERNAME)
            SignUpTextField(SignUpTextFieldType.EMAIL)
            SignUpTextField(SignUpTextFieldType.PASSWORD)
            SignUpTextField(SignUpTextFieldType.PASSWORD_CONFIRM)
        }

        // then
        composeTestRule
            .onNodeWithText(SignUpTextFieldType.USERNAME.hint)
            .assertExists()

        composeTestRule
            .onNodeWithText(SignUpTextFieldType.EMAIL.hint)
            .assertExists()

        composeTestRule
            .onNodeWithText(SignUpTextFieldType.PASSWORD.hint)
            .assertExists()

        composeTestRule
            .onNodeWithText(SignUpTextFieldType.PASSWORD_CONFIRM.hint)
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
}