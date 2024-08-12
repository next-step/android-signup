package nextstep.signup.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.core.validation.EmailValidationResult
import nextstep.signup.ui.component.EmailTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextTextFieldTest {

    private val textFieldValue = mutableStateOf("")
    private val emailValidationResult = mutableStateOf(EmailValidationResult.VALID)
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                email = textFieldValue.value,
                onEmailChange = { textFieldValue.value = it },
                emailValidationResult = emailValidationResult.value
            )
        }
    }

    @Test
    fun 입력이_없다면_아무런에러가_노출되면안된다() {
        textFieldValue.value = ""
        emailValidationResult.value = EmailValidationResult.VALID

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_invalid_email))
            .assertDoesNotExist()
    }

    @Test
    fun 이메일이_올바른_형식일_때_에러가_없다() {
        textFieldValue.value = "dlwlgns1240@gmail.com"
        emailValidationResult.value = EmailValidationResult.VALID

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_invalid_email))
            .assertDoesNotExist()
    }

    @Test
    fun 이메일은_올바른_형식이어야_한다() {
        textFieldValue.value = "invalid_email"
        emailValidationResult.value = EmailValidationResult.INVALID_FORMAT

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_invalid_email))
            .assertExists()
    }
}
