package nextstep.signup.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    private val passwordFieldValue = mutableStateOf("")
    private val passwordConfirmFieldValue = mutableStateOf("")
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                password = passwordFieldValue.value,
                onPasswordConfirmChange = { passwordFieldValue.value = it },
                passwordConfirmValue = passwordConfirmFieldValue.value,
            )
        }
    }


    @Test
    fun 비밀번호가_일치할_때_에러가_없다() {
        passwordConfirmFieldValue.value = "CorrectPassword123"
        passwordFieldValue.value = "CorrectPassword123"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_mismatch_error))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_일치해야_한다() {
        passwordConfirmFieldValue.value = "CorrectPassword123"
        passwordFieldValue.value = "WrongPassword"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_mismatch_error))
            .assertExists()
    }
}