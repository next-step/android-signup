package nextstep.signup

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.ui.theme.screen.SignUpTextField
import nextstep.signup.ui.theme.screen.validator.NameValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NameInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "UserName",
                "김컴포즈",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                NameValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2자보다_작으면_에러가노출() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "UserName",
                "김",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                NameValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 사용자_이름이_5자보다_크면_에러가노출() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "UserName",
                "김컴포즈입니다",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                NameValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 사용자_이름에는_숫자나_기호가_포함될_수_없다() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "UserName",
                "김컴포즈",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                NameValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에는_숫자가_포함되면_에러가_노출된다() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "UserName",
                "1김컴포즈",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                NameValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }

    @Test
    fun 사용자_이름에는_기호가_포함되면_에러가_노출된다() {
        // when
        composeTestRule.setContent {
            SignUpTextField(
                "UserName",
                "!김컴포즈",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                NameValidator()
            ) {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }
}
