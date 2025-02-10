package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.util.ValidationUtil.PASSWORD_FORMAT_ERROR
import nextstep.signup.util.ValidationUtil.PASSWORD_LENGTH_ERROR
import nextstep.signup.util.ValidationUtil.setPasswordErrorMessage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordInputField(
                value = password.value,
                errorMessage = setPasswordErrorMessage(password.value),
                onValueChange = { password.value = it }
            )
        }
    }

    @Test
    fun `비밀번호는_8에서_16자여야_한다`() {
        // when
        password.value = "1234567890"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호가_8에서_16자가_아니라면_에러메세지가_표시된다`() {
        // when
        password.value = "1234567"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun `비밀번호는_영문자와_숫자가_포함돼야_한다`() {
        // when
        password.value = "a1234567890"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호는_영문자와_숫자가_포함되지_않으면_에러메세지가_표시된다`() {
        // when
        password.value = "1234567890"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }
}
