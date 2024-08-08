package nextstep.signup.feature.signup

import android.content.Context
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun hasUsernameFieldHint() {
        composeTestRule.setContent {
            SignUpScreen()
        }

        composeTestRule.onNodeWithTag("username")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_username)))
    }

    @Test
    fun hasEmailFieldHint() {
        composeTestRule.setContent {
            SignUpScreen()
        }

        composeTestRule.onNodeWithTag("email")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_email)))
    }

    @Test
    fun hasPasswordFieldHint() {
        composeTestRule.setContent {
            SignUpScreen()
        }

        composeTestRule.onNodeWithTag("password")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_password)))
    }

    @Test
    fun hasPasswordConfirmFieldHint() {
        composeTestRule.setContent {
            SignUpScreen()
        }

        composeTestRule.onNodeWithTag("passwordConfirm")
            .assertExists()
            .assert(hasText(context.getString(R.string.sign_up_password_confirm)))
    }
}
