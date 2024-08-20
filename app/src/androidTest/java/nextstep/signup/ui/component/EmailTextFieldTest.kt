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
    private val validationResult = mutableStateOf(EmailValidationResult.Valid)

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                email = email.value,
                onEmailChange = { email.value = it },
                validationResult = validationResult.value
            )
        }
    }

    @Test
    fun 이메일을_입력하지_않으면_에러메시지가_노출되지_않는다() {
        // given
        val inputEmail = ""

        // when
        email.value = inputEmail
        validationResult.value = EmailValidationResult.Valid

        // then
        composeTestRule
            .onNodeWithText(EMAIL_TYPE_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // given
        val emailList = listOf("thxallgrace", "thxallgrace@github")

        emailList.forEach {
            // when
            email.value = it
            validationResult.value = EmailValidationResult.InvalidFormat

            // then
            composeTestRule
                .onNodeWithText(EMAIL_TYPE_ERROR)
                .assertExists()
        }
    }

    companion object {
        private const val EMAIL_TYPE_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
