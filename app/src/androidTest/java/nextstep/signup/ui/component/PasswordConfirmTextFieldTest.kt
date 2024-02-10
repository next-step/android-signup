package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val confirmPassword = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                password = password.value,
                confirmPassword = confirmPassword.value,
                onPasswordChange = { confirmPassword.value = it }
            )
        }
    }

    @Test
    fun 비밀번호가_모두_일치하면_에러메시지가_노출되지_않는다() {
        // when
        password.value = "1q2w3e4r!"
        confirmPassword.value = "1q2w3e4r!"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DIFF_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_일치하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "1234"
        confirmPassword.value = "12345678"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DIFF_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_비어있으면_에러메시지가_노출되지_않는다() {
        // when
        password.value = ""
        confirmPassword.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DIFF_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD_DIFF_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
