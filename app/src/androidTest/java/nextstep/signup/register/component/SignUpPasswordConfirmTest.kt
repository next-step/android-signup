package nextstep.signup.register.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpPasswordConfirmTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

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
            .onNodeWithText(
                context.getString(R.string.sign_up_error_password_confirm)
            ).assertDoesNotExist()
    }

    @Test
    fun should_show_error_when_not_equals_password() {
        //given
        passwordConfirm = "qwer1235"

        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_password_confirm)
            ).assertIsDisplayed()
    }

    @Test
    fun should_not_show_error_when_equals_password() {
        //given
        passwordConfirm = "qwer1234"

        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_password_confirm)
            ).assertDoesNotExist()
    }

    companion object {
        private const val MOCK_PASSWORD = "qwer1234"
    }
}