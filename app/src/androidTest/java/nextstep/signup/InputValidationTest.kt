package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import nextstep.signup.signup.component.SignUpInputForm
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var nameFieldValue = mutableStateOf(TextFieldValue(""))
    private var nameErrorMessage = mutableStateOf("")

    private var emailFieldValue = mutableStateOf(TextFieldValue(""))
    private var emailErrorMessage = mutableStateOf("")

    private var passwordFieldValue = mutableStateOf(TextFieldValue(""))
    private var passwordErrorMessage = mutableStateOf("")


    @Before
    fun setup() {
        composeTestRule.setContent {
            Column {
                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_name),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = nameFieldValue.value,
                    onValueChange = { newTextFieldValue ->
                        nameFieldValue.value = newTextFieldValue
                        nameErrorMessage.value = isNameValid(newTextFieldValue.text)
                    },
                    errorMessage = nameErrorMessage.value
                )

                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_email),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = emailFieldValue.value,
                    onValueChange = { newTextFieldValue ->
                        emailFieldValue.value = newTextFieldValue
                        emailErrorMessage.value = isEmailValid(newTextFieldValue.text)
                    },
                    errorMessage = emailErrorMessage.value
                )

                SignUpInputForm(
                    placeHolderText = stringResource(R.string.signup_main_input_password),
                    keyboardType = KeyboardType.Text,
                    textFieldValue = passwordFieldValue.value,
                    onValueChange = { newTextFieldValue ->
                        passwordFieldValue.value = newTextFieldValue
                        passwordErrorMessage.value = isEmailValid(newTextFieldValue.text)
                    },
                    errorMessage = passwordErrorMessage.value
                )
            }

        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        nameFieldValue.value = TextFieldValue("김컴포즈")
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        nameFieldValue.value = TextFieldValue("김컴포즈입니다")
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }
    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // when
        nameFieldValue.value = TextFieldValue("김무1무")
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일이_올바른_형식이_아니면_에러메시지가_노출된다() {
        // when
        emailFieldValue.value = TextFieldValue("testasd")
        emailErrorMessage.value = isEmailValid(emailFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }
    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        passwordFieldValue.value = TextFieldValue("12das34")
        passwordErrorMessage.value = isPasswordValid(passwordFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }
    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // when
        passwordFieldValue.value = TextFieldValue("123456789")
        passwordErrorMessage.value = isPasswordValid(passwordFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }


    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}
