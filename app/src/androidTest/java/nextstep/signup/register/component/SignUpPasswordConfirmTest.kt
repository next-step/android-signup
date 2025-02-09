package nextstep.signup.register.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpPasswordConfirmTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var passwordConfirm by mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextFiled.PasswordConfirm(
                passwordConfirm = passwordConfirm,
                onChangedPasswordConfirm = { passwordConfirm = it },
                isShowError = (passwordConfirm != MOCK_PASSWORD) && passwordConfirm.isNotEmpty()
            )
        }
    }

    @Test
    fun should_not_show_error_when_empty_password_confirm() {
        //given
        passwordConfirm = ""

        //then
        composeTestRule
            .onNodeWithText(ERROR_PASSWORD_CONFIRM)
            .assertDoesNotExist()
    }

    @Test
    fun should_show_error_when_not_equals_password() {
        //given
        passwordConfirm = "qwer1235"

        //then
        composeTestRule
            .onNodeWithText(ERROR_PASSWORD_CONFIRM)
            .assertIsDisplayed()
    }

    @Test
    fun should_not_show_error_when_equals_password() {
        //given
        passwordConfirm = "qwer1234"

        //then
        composeTestRule
            .onNodeWithText(ERROR_PASSWORD_CONFIRM)
            .assertDoesNotExist()
    }

    companion object {
        private const val MOCK_PASSWORD = "qwer1234"
        private const val ERROR_PASSWORD_CONFIRM = "비밀번호가 일치하지 않습니다."
    }
}