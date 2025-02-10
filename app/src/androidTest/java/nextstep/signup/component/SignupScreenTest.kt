package nextstep.signup.component

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * @author : interworks_aos
 * @CreatedDate : 2025. 2. 10. 오후 5:05
 * @PackageName : nextstep.signup.component
 * @ClassName: SignupScreenTest
 * @Description:
 */
@RunWith(Parameterized::class)
class SignupScreenTest(
    private val userName: String,
    private val email: String,
    private val password: String,
    private val passwordConfirm: String,
) {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignupScreen(onClickSignUp = {})
        }
    }

    @Test
    fun `모든입력이_유효하면_버튼이_활성화된다`() {
        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(userName)

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(email)

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(password)

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput(passwordConfirm)

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }


    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf("컴포즈", "android@gmail.com", "1q2w3e4r", "1q2w3e4r"),
            )
        }
    }
}
