package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UserNameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            Column {
                UserNameTextField(
                    text = username.value,
                    onTextValueChange = { username.value = it }
                )

                EmailTextField(
                    text = email.value,
                    onTextValueChange = { email.value = it }
                )

                PasswordTextField(
                    text = password.value,
                    onTextValueChange = { password.value = it }
                )

                PasswordConfirmTextField(
                    text = passwordConfirm.value,
                    onTextValueChange = { passwordConfirm.value = it },
                    password = password.value
                )
            }
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(userNameErrorMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(userNameErrorMsg)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "oyj7677@gmail"

        // then
        composeTestRule
            .onNodeWithText(emailErrorMsg)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르다면_에러메시지가_노출되지_않는다() {
        // when
        email.value = "oyj7677@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(emailErrorMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_8에서_16자이고_영문_숫자_조합이어야_한다() {
        // when
        password.value = "12345678dd"

        // then
        composeTestRule
            .onNodeWithText(passwordErrorMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        password.value = "12345678901234567"

        // then
        composeTestRule
            .onNodeWithText(passwordErrorMsg)
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않는다면_에러메시지가_노출된다() {
        // when
        password.value = "12345678"

        // then
        composeTestRule
            .onNodeWithText(passwordErrorMsg)
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "12345678@"

        // then
        composeTestRule
            .onNodeWithText(passwordErrorMsg)
            .assertExists()
    }


    companion object {
        private const val userNameErrorMsg = "이름은 2~5자여야 합니다.\n이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val emailErrorMsg = "이메일 형식이 올바르지 않습니다."
        private const val passwordErrorMsg =
            "비밀번호는 8~16자, 영문, 숫자 조합이어야 합니다.\n비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val passwordConfirmErrorMsg = "비밀번호가 일치하지 않습니다."
    }
}