package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.component.EmailTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailValidationUITest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                value = email.value,
                onValueChange = {},
                errorMessage = InputValidation.validateEmail(email.value).resourceId
                    ?.let { stringResource(it) },
            )
        }
    }

    @Test
    fun 이메일_형식을_지켜야_한다() {
        email.value = "com_compose@android.com"

        composeTestRule.onNodeWithText(EMAIL_INVALID_FORMAT_ERROR).assertDoesNotExist()
    }

    @Test
    fun 이메일_형식을_지키지_않으면_에러메시지가_노출된다() {
        email.value = "com_compose.com"

        composeTestRule.onNodeWithText(EMAIL_INVALID_FORMAT_ERROR).assertExists()
    }


    companion object {
        private const val EMAIL_INVALID_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}