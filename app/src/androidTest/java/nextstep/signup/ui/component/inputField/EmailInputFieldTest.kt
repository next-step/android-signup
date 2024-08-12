package nextstep.signup.ui.component.inputField

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailInputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val email = mutableStateOf("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            EmailInputField(
                email = email.value,
                onValueChange = { email.value = it },
                onValidationSuccess = {}
            )
        }
    }

    @Test
    fun 이메일은_형식에_맞아야_한다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_email_input_validation_message)

        // when
        email.value = "sjjeong1225@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일이_형식에_맞지_않으면_에러메시지가_노출된다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_email_input_validation_message)

        // when
        email.value = "sjjeong1225.gmail.com"

        // then
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }

}
