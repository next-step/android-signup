package nextstep.signup

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.userregister.widget.UserRegisterButton
import nextstep.signup.util.ValidationUtil.checkIsAllPassValidation
import org.junit.Rule
import org.junit.Test

class UserRegisterButtonValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setUserRegisterButton(
        userName: String,
        email: String,
        password: String,
        passwordConfirm: String,
    ) {
        composeTestRule.setContent {
            UserRegisterButton(
                enabled = checkIsAllPassValidation(
                    userName = userName,
                    email = email,
                    password = password,
                    passwordConfirm = passwordConfirm
                ),
                onButtonClick = {},
            )
        }
    }

    @Test
    fun `모든_필드의_값이_유효성_검사를_통과한다면_버튼이_활성화된다`() {
        // given
        val userName = "abc"
        val email = "abc@email.com"
        val password = "abc123456789"
        val passwordConfirm = "abc123456789"

        // when
        setUserRegisterButton(userName, email, password, passwordConfirm)

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun `하나의_필드라도_값이_비어있으면_버튼은_비활성화_된다`() {
        // given
        val userName = ""
        val email = "abc@email.com"
        val password = "abc123456789"
        val passwordConfirm = "abc123456789"

        // when
        setUserRegisterButton(userName, email, password, passwordConfirm)

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun `하나의_필드라도_유효성_검사를_통과하지_못한다면_버튼은_비활성화_된다`() {
        // given
        val userName = "abc!"
        val email = "abc@email.com"
        val password = "abc123456789"
        val passwordConfirm = "abc123456789"

        // when
        setUserRegisterButton(userName, email, password, passwordConfirm)

        // then
        composeTestRule.onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}
