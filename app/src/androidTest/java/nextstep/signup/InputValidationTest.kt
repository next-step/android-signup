package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import nextstep.signup.signup.component.SignUpInputForm
import nextstep.signup.signup.util.ValidationUtil.PASSWORD_FORMAT_ERROR
import nextstep.signup.signup.util.ValidationUtil.USERNAME_LENGTH_ERROR
import nextstep.signup.signup.util.ValidationUtil.USERNAME_FORMAT_ERROR
import nextstep.signup.signup.util.ValidationUtil.EMAIL_FORMAT_ERROR
import nextstep.signup.signup.util.ValidationUtil.PASSWORD_LENGTH_ERROR
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        val nameFieldValue = mutableStateOf(TextFieldValue("김컴포즈"))
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            Column {
                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_name),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = nameFieldValue.value,
                    onValueChange = {},
                    errorMessage = nameErrorMessage.value
                )
            }
        }
        // when
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val nameFieldValue = mutableStateOf(TextFieldValue("김컴포즈입니다"))
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            Column {
                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_name),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = nameFieldValue.value,
                    onValueChange = {},
                    errorMessage = nameErrorMessage.value
                )
            }
        }
        // when
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // given
        val nameFieldValue = mutableStateOf(TextFieldValue("김무1무"))
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            Column {
                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_name),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = nameFieldValue.value,
                    onValueChange = {},
                    errorMessage = nameErrorMessage.value
                )
            }
        }
        // when
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일이_올바른_형식이_아니면_에러메시지가_노출된다() {
        // given
        val emailFieldValue = mutableStateOf(TextFieldValue("testasd"))
        val emailErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            Column {
                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_email),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = emailFieldValue.value,
                    onValueChange = {},
                    errorMessage = emailErrorMessage.value
                )
            }
        }
        // when
        emailErrorMessage.value = isEmailValid(emailFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // given
        val passwordFieldValue = mutableStateOf(TextFieldValue("12das34"))
        val passwordErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            Column {
                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_password),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = passwordFieldValue.value,
                    onValueChange = {},
                    errorMessage = passwordErrorMessage.value
                )
            }
        }
        // when
        passwordErrorMessage.value = isPasswordValid(passwordFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // given
        val passwordFieldValue = mutableStateOf(TextFieldValue("123456789"))
        val passwordErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            Column {
                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_password),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = passwordFieldValue.value,
                    onValueChange = {},
                    errorMessage = passwordErrorMessage.value
                )
            }
        }
        // when
        passwordErrorMessage.value = isPasswordValid(passwordFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }
}