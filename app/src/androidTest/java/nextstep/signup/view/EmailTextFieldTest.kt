package nextstep.signup.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val email = mutableStateOf("")

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                }
            )
        }
    }

    @Test
    fun 이메일은_형식에_맞아야_한다() {
        // when
        email.value = "abcd@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_email_format_error))
            .assertDoesNotExist()
    }

    @Test
    fun 이메일이_형식에_맞지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "abcd@gm"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_email_format_error))
            .assertExists()
    }

}