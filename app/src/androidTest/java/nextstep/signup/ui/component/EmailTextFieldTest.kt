package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")
    private val emailValidation = EmailValidation()

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                value = email.value,
                onValueChange = { email.value = it },
                validationResult = emailValidation.isValid(email.value),
            )
        }
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // given
        email.value = "test"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르면_에러메시지가_노출되지_않는다() {
        // given
        email.value = "test@yopmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_비어있으면_에러메시지가_노출되지_않는다() {
        // given
        email.value = ""

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
