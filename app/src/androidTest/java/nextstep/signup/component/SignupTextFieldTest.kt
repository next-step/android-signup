package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.validator.SignupInfoValidator
import org.junit.Rule
import org.junit.Test

class SignupTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `컴포넌트가_렌더링_된다`() {
        // given
        val label = "UserName"
        composeTestRule.setContent {
            SignupTextField(
                label = label,
                text = "text",
                onValueChange = { },
                visualTransformation = VisualTransformation.None,
                modifier = Modifier.testTag(label)
            )
        }

        // then
        composeTestRule
            .onNodeWithTag(label)
            .assertExists()
    }

    @Test
    fun `비밀번호가_일치하지않으면_에러문구가_보인다`() {
        // given
        val passwordLabel = "password"
        val passwordConfirmLabel = "passwordConfirm"

        composeTestRule.setContent {
            val (password, setPassword) = remember { mutableStateOf("") }
            val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

            SignupTextField(
                label = passwordLabel,
                text = password,
                onValueChange = setPassword,
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.Password,
                modifier = Modifier.testTag(passwordLabel)
            )

            SignupTextField(
                label = passwordConfirmLabel,
                text = passwordConfirm,
                onValueChange = setPasswordConfirm,
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.PasswordConfirm { password },
                modifier = Modifier.testTag(passwordConfirmLabel)
            )
        }
        val originPassword = "1q2w3e4r5t"
        val confirmPassword = "1q2w3e4r5"

        composeTestRule
            .onNodeWithTag(passwordLabel)
            .performTextInput(originPassword)

        composeTestRule
            .onNodeWithTag(passwordConfirmLabel)
            .performTextInput(confirmPassword)

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertIsDisplayed()
    }
}
