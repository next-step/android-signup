package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.component.textfield.PasswordConfirmTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = "dooly1234"
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField (
                password = password,
                passwordConfirm = passwordConfirm.value,
                onPasswordConfirmChange = {}
            )
        }
    }

    @Test
    fun 비밀번호와_비밀번호확인란_값은_같아야_한다() {
        // when
        passwordConfirm.value = password

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_값이_같지_않으면_에러메시지가_노출된다() {
        // when
        passwordConfirm.value = password + "12"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val EMAIL_FORMAT_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
