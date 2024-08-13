package nextstep.signup.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.ui.signup.EmailField
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class EmailFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private var email by mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailField(
                email = email,
                onEmailChange = { email = it }
            )
        }
    }

    @Test
    fun 이메일_형식이_정확해야_한다() {
        // when
        email = "kyudong3@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_email))
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_정확하지_않으면_에러_노출() {
        // when
        email = "kyudong3@gmail"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_email))
            .assertExists()
    }

    @Test
    fun 빈_값인_경우_에러가_노출되지_않는다() {
        // when
        email = ""

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_email))
            .assertDoesNotExist()
    }
}
