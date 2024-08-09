package nextstep.signup

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
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
    var context: Context = ApplicationProvider.getApplicationContext()
    private val username = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            Column {
                UserNameTextField(text = username.value,
                    onTextValueChange = { username.value = it })

                EmailTextField(text = email.value, onTextValueChange = { email.value = it })

                PasswordTextField(text = password.value,
                    onTextValueChange = { password.value = it })

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
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_user_name))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_user_name)).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "oyj7677@gmail"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_email)).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르다면_에러메시지가_노출되지_않는다() {
        // when
        email.value = "oyj7677@gmail.com"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_email))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_8에서_16자이고_영문_숫자_조합이어야_한다() {
        // when
        password.value = "12345678dd"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        password.value = "12345678901234567"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않는다면_에러메시지가_노출된다() {
        // when
        password.value = "12345678"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "12345678@"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하지_않는다면_에러메시지가_노출된다() {
        // when
        password.value = "12345678dd"
        passwordConfirm.value = "12345678dd!"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_password_confirm))
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치한다면_에러메시지가_노출되지_않는다() {
        // when
        password.value = "12345678dd"
        passwordConfirm.value = "12345678dd"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_password_confirm))
            .assertDoesNotExist()
    }
}