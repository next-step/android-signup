package nextstep.signup.ui.component.textfield

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.component.SignupForm
import nextstep.signup.ui.util.EmailValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")
    private val emailValidator = EmailValidator()

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignupForm(
                inputValue = email.value,
                onInputChange = { email.value = it },
                validResult = emailValidator.validate(email.value),
            )
        }
    }

    @Test
    fun 유효한_이메일_형식인_경우_에러메세지가_노출되지_않는다() {
        // when
        email.value = "user@domain.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 잘못된_이메일_형태가_입력된_경우_에러메세지가_노출된다() {
        // 잘못된 이메일 형태
        val invalidEmails = listOf(
            "",
            "plaintext",
            "missingdomain@",
            "user@domain!#.com",
            "user@domain",
            "user@.com",
        )

        invalidEmails.forEach { invalidEmail ->
            // when
            email.value = invalidEmail

            // then
            composeTestRule
                .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
                .assertExists()
        }
    }

    companion object {
        private const val EMAIL_INVALID_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}