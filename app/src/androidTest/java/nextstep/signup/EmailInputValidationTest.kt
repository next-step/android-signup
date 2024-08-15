package nextstep.signup

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.ui.theme.screen.SignUpTextField
import nextstep.signup.ui.theme.screen.validator.EmailValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
    }

    @Test
    fun 이메일_형식이_올바른_경우_성공처리된다() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "Email",
                "mioyo2@gmail.com",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                EmailValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않은경우_에러메시지가_노출된다() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "Email",
                "mioyo2gmail.com",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                EmailValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }
}
