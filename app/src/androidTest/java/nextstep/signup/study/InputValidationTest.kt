package nextstep.signup.study

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UsernameTextField
import nextstep.signup.ui.component.emailFormatNotMatchErrorText
import nextstep.signup.ui.component.passwordFormatNotMatchErrorText
import nextstep.signup.ui.component.passwordLengthErrorText
import nextstep.signup.ui.component.userNameLengthErrorText
import nextstep.signup.ui.component.userNameNotMatchErrorText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val usernameState = mutableStateOf("")
    private val emailState = mutableStateOf("")
    private val passwordState = mutableStateOf("")
    private val passwordConfirmState = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(usernameState)
            EmailTextField(emailState)
            PasswordTextField(passwordState)
            PasswordConfirmTextField(passwordConfirmState, passwordProvider = passwordState::value)
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        usernameState.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(userNameLengthErrorText)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        usernameState.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(userNameLengthErrorText)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자가_포함되어_있으면_에러메시지가_노출된다() {
        // when
        usernameState.value = "김1컴포"

        // then
        composeTestRule
            .onNodeWithText(userNameNotMatchErrorText)
            .assertExists()
    }

    @Test
    fun 이메일_형식이면_에러메시지가_노출되지_않는다() {
        // when
        emailState.value = "test@test.com"

        // then
        composeTestRule
            .onNodeWithText(emailFormatNotMatchErrorText)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_아니면_에러메시지가_노출된다() {
        // when
        emailState.value = "test"

        // then
        composeTestRule
            .onNodeWithText(emailFormatNotMatchErrorText)
            .assertExists()
    }

    @Test
    fun 비밀번호_8_16자면_에러메시지가_노출되지않는다() {
        // when
        passwordState.value = "12345678"

        // then
        composeTestRule
            .onNodeWithText(passwordLengthErrorText)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_8_16자_아니면_에러메시지가_노출된다() {
        // when
        passwordState.value = "1234"

        // then
        composeTestRule
            .onNodeWithText(passwordLengthErrorText)
            .assertExists()
    }

    @Test
    fun 비밀번호_영문과_기호가_없으면_에러메시지가_노출된다() {
        // when
        passwordState.value = "123456789"

        // then
        composeTestRule
            .onNodeWithText(passwordFormatNotMatchErrorText)
            .assertExists()
    }

    @Test
    fun 비밀번호_영문과_기호가_있으면_에러메시지가_노출된다() {
        // when
        passwordState.value = "a123456789!@"

        // then
        composeTestRule
            .onNodeWithText(passwordFormatNotMatchErrorText)
            .assertDoesNotExist()
    }
}