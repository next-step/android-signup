package nextstep.signup.feature.signup

import android.content.Context
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.isNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
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

    @Test
    fun 최초에_입력값이_없을땐_회원가입_버튼이_비활성화된다() {
        composeTestRule.onNodeWithTag("signUpButton")
            .assertExists()
            .assert(isNotEnabled())
    }

    @Test
    fun 모든_유효성_검사가_통과하면_회원가입_버튼이_활성화된다() {
        composeTestRule.onNodeWithTag("username")
            .performTextInput("dino")
        composeTestRule.onNodeWithTag("email")
            .performTextInput("dino@next.step")
        composeTestRule.onNodeWithTag("password")
            .performTextInput("password1234")
        composeTestRule.onNodeWithTag("passwordConfirm")
            .performTextInput("password1234")

        composeTestRule.onNodeWithTag("signUpButton")
            .assertExists()
            .assert(isEnabled())
    }

    @Test
    fun 이름을_입력하지_않으면_회원가입_버튼이_비활성화된다() {
        composeTestRule.onNodeWithTag("email")
            .performTextInput("dino@next.step")
        composeTestRule.onNodeWithTag("password")
            .performTextInput("password1234")
        composeTestRule.onNodeWithTag("passwordConfirm")
            .performTextInput("password123")

        composeTestRule.onNodeWithTag("signUpButton")
            .assertExists()
            .assert(isNotEnabled())
    }

    @Test
    fun 이메일을_입력하지_않으면_회원가입_버튼이_비활성화된다() {
        composeTestRule.onNodeWithTag("username")
            .performTextInput("dino")
        composeTestRule.onNodeWithTag("password")
            .performTextInput("password1234")
        composeTestRule.onNodeWithTag("passwordConfirm")
            .performTextInput("password123")

        composeTestRule.onNodeWithTag("signUpButton")
            .assertExists()
            .assert(isNotEnabled())
    }

    @Test
    fun 비밀번호와_비밀번호확인이_일치하지_않으면_회원가입_버튼이_비활성화된다() {
        composeTestRule.onNodeWithTag("username")
            .performTextInput("dino")
        composeTestRule.onNodeWithTag("email")
            .performTextInput("dino@next.step")
        composeTestRule.onNodeWithTag("password")
            .performTextInput("password1234")
        composeTestRule.onNodeWithTag("passwordConfirm")
            .performTextInput("password123")

        composeTestRule.onNodeWithTag("signUpButton")
            .assertExists()
            .assert(isNotEnabled())
    }
}
