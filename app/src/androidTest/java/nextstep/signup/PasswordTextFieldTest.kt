package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import nextstep.signup.view.PasswordTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PasswordTextField()
        }
    }

    @Test
    fun 비밀번호가_올바르게_입력되어야_한다() {
        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_LENGTH_ERROR)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        val passwords =
            listOf("q1w2e3r4", "q1w2e3r4q1w2e3r4")

        passwords.forEach { password ->
            // when
            composeTestRule.onNodeWithText(PASSWORD).performTextClearance()
            composeTestRule.onNodeWithText(PASSWORD).performTextInput(password)

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_INVALID_LENGTH_ERROR)
                .assertDoesNotExist()
        }
    }

    @Test
    fun 비밀번호는_8에서_16자가_아니면_에러메시지가_노출된다() {
        val passwords =
            listOf("q1w2e3r", "q1w2e3r4q1w2e3r45")

        passwords.forEach { password ->
            // when
            composeTestRule.onNodeWithText(PASSWORD).performTextClearance()
            composeTestRule.onNodeWithText(PASSWORD).performTextInput(password)

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_INVALID_LENGTH_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        val passwords =
            listOf("qqqqwwww", "11112222")

        // when
        passwords.forEach { password ->
            composeTestRule.onNodeWithText(PASSWORD).performTextClearance()
            composeTestRule.onNodeWithText(PASSWORD).performTextInput(password)

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_INVALID_ERROR)
                .assertExists()
        }
    }


    companion object {
        private const val PASSWORD_INVALID_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_INVALID_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD = "Password"
    }
}