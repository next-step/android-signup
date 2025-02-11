package nextstep.signup.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.utils.SignUpValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val password = mutableStateOf("")

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                value = password.value,
                errorType = SignUpValidator.Password(password.value).validate(),
                onValueChange = {
                    password.value = it
                }
            )
        }
    }

    @Test
    fun 패스워드는_8에서_16자_이어야_한다() {
        // when
        password.value = "1234asdf"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 패스워드가_8에서_16자_이내가_아니면_에러_메시지가_노출된다() {
        // when
        password.value = "1234as"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_length_error))
            .assertExists()
    }

    @Test
    fun 패스워드는_영문과_숫자를_포함해야_한다() {
        // when
        password.value = "1234asdf"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_format_error))
            .assertDoesNotExist()
    }

    @Test
    fun 패스워드에_영문과_숫자가_포함되지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "12345678"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_format_error))
            .assertExists()
    }


}