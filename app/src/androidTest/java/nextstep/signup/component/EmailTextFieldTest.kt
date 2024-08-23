package nextstep.signup.component

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.util.validation.Validator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")
    private val emailValidationResult =
        derivedStateOf { Validator.emailValidate(email.value) }

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                text = email.value,
                validationResult = emailValidationResult.value,
                onValueChange = {
                    email.value = it
                }
            )
        }
    }

    @Test
    fun `이메일이_형식에_맞지_않으면_에러메시지가_노출된다`() {
        email.value = "test!test.com"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_email_regex_error))
            .assertExists()
    }

    @Test
    fun `이메일이_형식에_맞으면_에러메시지가_노출되지_않는다`() {
        email.value = "test@test.com"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_regex_error))
            .assertDoesNotExist()
    }
}