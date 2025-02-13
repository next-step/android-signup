package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.screen.signup.component.EmailTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField (
                email = email.value,
                onEmailChange = {}
            )
        }
    }

    @Test
    fun 이메일_형식을_지켜야_한다() { // abc@abc.de
        // when
        email.value = "hyemdooly@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식을_지키지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "hyemdooly_gmailcom"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
