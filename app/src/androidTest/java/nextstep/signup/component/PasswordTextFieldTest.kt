package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                text = password.value,
                onValueChange = {
                    password.value = it
                }
            )
        }
    }

    @Test
    fun `비밀번호는_8에서_16자여야_한다`() {
        password.value = "1q2w3e4r"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다`() {
        password.value = "1q2w3e4"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_password_length_error))
            .assertExists()
    }

    @Test
    fun `비밀번호에_숫자가_없으면_에러메시지가_노출된다`() {
        password.value = "qwerasdf"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_password_regex_error))
            .assertExists()
    }

    @Test
    fun `비밀번호에_영어가_있으면_에러메시지가_노출된다`() {
        password.value = "12345678"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_password_regex_error))
            .assertExists()
    }
}