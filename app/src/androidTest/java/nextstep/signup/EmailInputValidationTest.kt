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

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(value = email.value, onValueChange = {})
        }
    }

    @Test
    fun 이메일_형식이_맞으면_에러메세지가_노출되지_않는다() {
        // when
        email.value = "test@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식에_골뱅이표가_없으면_에러메세지가_노출된다() {
        // when
        email.value = "testgmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식에_점이_없으면_에러메세지가_노출된다() {
        // when
        email.value = "test@gmailcom"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식에_골뱅이표와_점_사이에_문자가_없으면_에러메세지가_노출된다() {
        // when
        email.value = "test@.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식에_빈문자열이면_에러메세지가_노출되지_않는다() {
        // when
        email.value = ""

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val EMAIL_INVALID_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
