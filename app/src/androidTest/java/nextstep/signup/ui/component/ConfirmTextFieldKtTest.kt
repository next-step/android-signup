package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ConfirmTextFieldKtTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val confirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                password = password.value,
                passwordConfirm = confirm.value,
                onValueChange = { value ->
                    confirm.value = value
                }
            )
        }
    }

    @Test
    fun 비밀번호가_일치하지_않으면_에러메시지가_노출됩니다() {
        // given

        // when
        password.value = "1234qwer"
        confirm.value = "1234qwea"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_NOT_CONFIRM_ERROR)
            .assertExists()
    }

    @Test
    fun 비어있으면_에러메시지가_노출되지_않는다() {
        // given

        // when
        confirm.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_NOT_CONFIRM_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD_NOT_CONFIRM_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
