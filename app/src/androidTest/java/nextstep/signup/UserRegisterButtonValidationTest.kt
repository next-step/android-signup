package nextstep.signup

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.userregister.widget.UserRegisterButton
import org.junit.Rule
import org.junit.Test

class UserRegisterButtonValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `모든_필드의_값이_유효성_검사를_통과한다면_버튼이_활성화된다`() {
        // given
        val state = UserRegisterState(
            userName = "abc",
            email = "abc@email.com",
            password = "abc123456789",
            passwordConfirm = "abc123456789",
        )


        // when
        composeTestRule.setContent {
            UserRegisterButton(
                state = state,
                onButtonClick = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun `하나의_필드라도_값이_비어있으면_버튼은_비활성화_된다`() {
        // given
        val state = UserRegisterState(
            userName = "",
            email = "abc@email.com",
            password = "abc123456789",
            passwordConfirm = "abc123456789",
        )

        // when
        composeTestRule.setContent {
            UserRegisterButton(
                state = state,
                onButtonClick = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun `하나의_필드라도_유효성_검사를_통과하지_못한다면_버튼은_비활성화_된다`() {
        // given
        val state = UserRegisterState(
            userName = "abc!",
            email = "abc@email.com",
            password = "abc123456789",
            passwordConfirm = "abc123456789",
        )

        // when
        composeTestRule.setContent {
            UserRegisterButton(
                state = state,
                onButtonClick = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}
