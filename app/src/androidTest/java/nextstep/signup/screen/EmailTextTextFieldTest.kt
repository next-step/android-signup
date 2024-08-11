package nextstep.signup.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextTextFieldTest {

    private val textFieldValue = mutableStateOf("")
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                email = textFieldValue.value,
                onEmailChange = { textFieldValue.value = it }
            )
        }
    }

    @Test
    fun 이메일은_올바른_형식이어야_한다() {
        textFieldValue.value = "invalid_email"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_invalid_email))
            .assertExists()
    }


    @Test
    fun 이메일이_올바른_형식일_때_에러가_없다() {
        textFieldValue.value = "dlwlgns1240@gmail.com"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_invalid_email))
            .assertDoesNotExist()
    }
}