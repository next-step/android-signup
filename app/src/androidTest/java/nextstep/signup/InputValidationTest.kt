package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.signup.component.SignUpInputForm
import nextstep.signup.signup.component.getSignUpInputFieldErrorMessage
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid
import org.junit.Rule
import org.junit.Test

class InputValidationTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        val nameFieldValue = mutableStateOf("김컴포즈")
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {

            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_name),
                keyboardType = KeyboardType.Text,
                inputText = nameFieldValue.value,
                onValueChange = {},
                errorMessage = nameErrorMessage.value
            )

        }
        // when
        nameErrorMessage.value = getSignUpInputFieldErrorMessage(
            context,
            isNameValid(nameFieldValue.value)
        )
        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_username_format))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val nameFieldValue = mutableStateOf("김컴포즈입니다")
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {

            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_name),
                keyboardType = KeyboardType.Text,
                inputText = nameFieldValue.value,
                onValueChange = {},
                errorMessage = nameErrorMessage.value
            )

        }
        // when
        nameErrorMessage.value = getSignUpInputFieldErrorMessage(
            context,
            isNameValid(nameFieldValue.value)
        )
        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_username_length))
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // given
        val nameFieldValue = mutableStateOf("김무1무")
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {

            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_name),
                keyboardType = KeyboardType.Text,
                inputText = nameFieldValue.value,
                onValueChange = {},
                errorMessage = nameErrorMessage.value
            )

        }
        // when
        nameErrorMessage.value = getSignUpInputFieldErrorMessage(
            context,
            isNameValid(nameFieldValue.value)
        )
        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_username_format))
            .assertExists()
    }

    @Test
    fun 이메일이_올바른_형식이_아니면_에러메시지가_노출된다() {
        // given
        val emailFieldValue = mutableStateOf("testasd")
        val emailErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_email),
                keyboardType = KeyboardType.Text,
                inputText = emailFieldValue.value,
                onValueChange = {},
                errorMessage = emailErrorMessage.value
            )
        }
        // when
        emailErrorMessage.value = getSignUpInputFieldErrorMessage(
            context,
            isEmailValid(emailFieldValue.value)
        )
        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_email_format))
            .assertExists()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // given
        val passwordFieldValue = mutableStateOf("12das34")
        val passwordErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_password),
                keyboardType = KeyboardType.Text,
                inputText = passwordFieldValue.value,
                onValueChange = {},
                errorMessage = passwordErrorMessage.value
            )
        }
        // when
        passwordErrorMessage.value = getSignUpInputFieldErrorMessage(
            context,
            isPasswordValid(passwordFieldValue.value)
        )
        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_password_length))
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // given
        val passwordFieldValue = mutableStateOf("123456789")
        val passwordErrorMessage = mutableStateOf("")
        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_password),
                keyboardType = KeyboardType.Text,
                inputText = passwordFieldValue.value,
                onValueChange = {},
                errorMessage = passwordErrorMessage.value
            )
        }
        // when
        passwordErrorMessage.value = getSignUpInputFieldErrorMessage(
            context,
            isPasswordValid(passwordFieldValue.value)
        )
        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_password_format))
            .assertExists()
    }

    @Test
    fun 입력_폼에_오류메시지입니다_라는_값이_들어가면_오류메시지입니다가_보여야_한다() {
        // given
        val inputText = mutableStateOf("")

        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = "",
                keyboardType = KeyboardType.Text,
                inputText = inputText.value,
                onValueChange = {},
                errorMessage = ""
            )
        }
        // when
        inputText.value = "오류메세지입니다"
        // then
        composeTestRule
            .onNodeWithText("오류메세지입니다")
            .assertExists()
    }
}