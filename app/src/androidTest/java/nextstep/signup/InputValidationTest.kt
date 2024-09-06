package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val email = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            Column {
                UsernameTextField(username = username)
                EmailTextField(email = email)
            }
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바른_경우() {
        // when
        email.value = "compose@nextstep.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않은_경우() {
        // when
        composeTestRule.onNodeWithTag("emailTextField").performTextInput("compose")

        // then
        composeTestRule
            .onNodeWithText(EMAIL_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_LETTER_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val EMAIL_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_LETTER_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_CONFIRM_ERROR = "비밀번호가 일치하지 않습니다."
    }
}