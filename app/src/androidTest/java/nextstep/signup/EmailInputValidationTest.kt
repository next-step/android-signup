package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.textfield.EmailTextField
import nextstep.signup.textfield.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")
    private val isEmailValid = mutableStateOf(true)

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(value = email.value, isValidFormat = isEmailValid.value, onValueChange = {})
        }
    }

    @Test
    fun 이메일이_빈문자열이_아니고_유효성이_true면_에러메세지가_노출되지_않는다() {
        // when
        email.value = "test@gmail.com"
        isEmailValid.value = true

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일이_빈문자열이_아니고_유효성이_false면_에러메세지가_노출된다() {
        // when
        email.value = "testgmail.com"
        isEmailValid.value = false

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일이_빈문자열이면_에러메세지가_노출되지_않는다() {
        // when
        email.value = ""
        isEmailValid.value = false

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val EMAIL_INVALID_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
