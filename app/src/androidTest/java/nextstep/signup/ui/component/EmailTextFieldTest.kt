package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.EmailValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")
    private val validationResult = mutableStateOf(EmailValidationResult.SUCCESS)

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                email = email.value,
                validationResult = validationResult.value,
                onEmailChange = { email.value = it }
            )
        }
    }

    @Test
    fun 이메일_형식이_올바르면_에러메시지가_노출되지_않는다() {
        // when
        email.value = "test@test.com"
        validationResult.value = EmailValidationResult.SUCCESS

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "김컴포즈입니다"
        validationResult.value = EmailValidationResult.INVALID_FORMAT

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일이_비어있으면_에러메시지가_노출되지_않는다() {
        // when
        email.value = ""
        validationResult.value = EmailValidationResult.INVALID_FORMAT

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
