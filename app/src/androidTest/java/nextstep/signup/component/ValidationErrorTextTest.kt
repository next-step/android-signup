package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.util.validation.ValidationErrorType
import nextstep.signup.util.validation.ValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ValidationErrorTextTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val validationResult = mutableStateOf<ValidationResult>(ValidationResult.Pending)
    private val lengthErrorResId = mutableStateOf<Int?>(null)
    private val regexErrorResId = mutableStateOf<Int?>(null)
    private val equalityErrorResId = mutableStateOf<Int?>(null)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            ValidationErrorText(
                validationResult = validationResult.value,
                lengthErrorResId = lengthErrorResId.value,
                regexErrorResId = regexErrorResId.value,
                equalityErrorResId = equalityErrorResId.value
            )
        }
    }

    @Test
    fun `Pending_상태일_땐_에러_메시지가_빈_텍스트가_노출된다`() {
        validationResult.value = ValidationResult.Pending
        lengthErrorResId.value = R.string.sign_up_user_name_length_error

        composeTestRule
            .onNodeWithText("")
            .assertExists()
    }

    @Test
    fun `Success_상태일_땐_에러_메시지가_빈_텍스트가_노출된다`() {
        validationResult.value = ValidationResult.Success
        lengthErrorResId.value = R.string.sign_up_user_name_length_error

        composeTestRule
            .onNodeWithText("")
            .assertExists()
    }

    @Test
    fun `Length_Error_상태일_땐_지정한_에러_메시지가_노출된다`() {
        validationResult.value = ValidationResult.Error(ValidationErrorType.LengthError)
        lengthErrorResId.value = R.string.sign_up_user_name_length_error

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_length_error))
            .assertExists()
    }

    @Test
    fun `Regex_Error_상태일_땐_지정한_에러_메시지가_노출된다`() {
        validationResult.value = ValidationResult.Error(ValidationErrorType.RegexError)
        regexErrorResId.value = R.string.sign_up_user_name_regex_error

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_regex_error))
            .assertExists()
    }

    @Test
    fun `Equality_Error_상태일_땐_지정한_에러_메시지가_노출된다`() {
        validationResult.value = ValidationResult.Error(ValidationErrorType.EqualityError)
        equalityErrorResId.value = R.string.sign_up_user_password_confirm_not_equal

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_password_confirm_not_equal))
            .assertExists()
    }

    @Test
    fun `Error_상태이지만_지정한_에러_메시지가_없을_경우_빈_텍스트가_노출된다`() {
        validationResult.value = ValidationResult.Error(ValidationErrorType.EqualityError)

        composeTestRule
            .onNodeWithText("")
            .assertExists()
    }
}