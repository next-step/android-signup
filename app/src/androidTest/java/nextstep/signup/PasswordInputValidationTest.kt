package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.util.ValidationUtil.setPasswordErrorMessage
import org.junit.Rule
import org.junit.Test

class PasswordInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setPasswordInputField(password: String) {
        composeTestRule.setContent {
            PasswordInputField(
                value = password,
                errorMessage = setPasswordErrorMessage(password),
                onValueChange = {}
            )
        }
    }

    @Test
    fun `비밀번호는_8에서_16자여야_한다`() {
        // given
        val password = "1234567890"

        // when
        setPasswordInputField(password)

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호가_8에서_16자가_아니라면_에러메세지가_표시된다`() {
        // given
        val password = "1234567"

        // when
        setPasswordInputField(password)

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun `비밀번호는_영문자와_숫자가_포함돼야_한다`() {
        // given
        val password = "a1234567890"

        // when
        setPasswordInputField(password)

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호는_영문자와_숫자가_포함되지_않으면_에러메세지가_표시된다`() {
        // given
        val password = "1234567890"

        // when
        setPasswordInputField(password)

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }
}
