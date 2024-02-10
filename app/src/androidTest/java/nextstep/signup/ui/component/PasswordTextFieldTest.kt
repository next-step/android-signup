package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = password.value,
                onPasswordChange = { password.value = it }
            )
        }
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // when
        password.value = "1q2w3e4r!"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        password.value = "1234"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_비어으면_에러메시지가_노출되지_않는다() {
        // when
        password.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호에_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "1234567890"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}
