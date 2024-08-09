package nextstep.signup.feature.signup

import android.content.Context
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignUpScreen()
        }
    }

    @Test
    fun hasUsernameFieldHint() {
        composeTestRule.onNodeWithTag("username")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_username)))
    }

    @Test
    fun hasEmailFieldHint() {
        composeTestRule.onNodeWithTag("email")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_email)))
    }

    @Test
    fun hasPasswordFieldHint() {
        composeTestRule.onNodeWithTag("password")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_password)))
    }

    @Test
    fun hasPasswordConfirmFieldHint() {
        composeTestRule.onNodeWithTag("passwordConfirm")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_password_confirm)))
    }
}
