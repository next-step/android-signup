package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
                validator = SignupInfoValidator.Username,
                modifier = Modifier.testTag(label)
            )
        }

        // then
        composeTestRule
            .onNodeWithTag(label)
            .assertExists()
    }

    @Test
    fun `이름_입력값은_그대로_보여진다`() {
        // given
        val label = "UserName"
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf("") }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.Username,
                modifier = Modifier.testTag(label)
            )
        }

        // when 이용우를 입력하면
        composeTestRule
            .onNodeWithTag(label)
            .performTextInput("이용우")

        // then 이용우가 보여지는가
        composeTestRule
            .onNodeWithText("이용우")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호의_입력값은_그대로_보이지_않는다`() {
        // given
        val label = "Password"
        composeTestRule.setContent {
            val (password, setPassword) = remember { mutableStateOf("") }
            SignupTextField(
                label = label,
                text = password,
                onValueChange = setPassword,
                visualTransformation = PasswordVisualTransformation(),
                validator = SignupInfoValidator.Password,
                modifier = Modifier.testTag(label)
            )
        }

        // when 비밀번호를 입력하면
        composeTestRule
            .onNodeWithTag(label)
            .performTextInput("아무도 알알랴줌")

        // then 비밀번호는 보여지지 않는다.
        composeTestRule
            .onNodeWithText("아무도 알알랴줌")
            .assertIsNotDisplayed()
    }

    @Test
    fun `이름이_2자에서_5자가_아니면_에러문구가_보인다`() {
        // given
        composeTestRule.setContent {
            SignupTextField(
                label = "label",
                text = "이름이뭐랍니까",
                onValueChange = { },
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.Username
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `이름에_숫자나_기호를_포함하면_에러문구가_보인다`() {
        // given
        composeTestRule.setContent {
            SignupTextField(
                label = "UserName",
                text = "이용우2",
                onValueChange = { },
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.Username
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `이메일_형식이_맞지않으면_에러문구가_보인다`() {
        // given
        composeTestRule.setContent {
            SignupTextField(
                label = "email",
                text = "raindragonn!gmail.com",
                onValueChange = { },
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.Email
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호가_8에서_16자가_아니면_에러문구가_보인다`() {
        // given
        composeTestRule.setContent {
            SignupTextField(
                label = "password",
                text = "123",
                onValueChange = { },
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.Password
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호에_영문과_숫자를_포함하지_않으면_에러문구가_보인다`() {
        // given
        composeTestRule.setContent {
            SignupTextField(
                label = "password",
                text = "가나다라마바사아",
                onValueChange = { },
                visualTransformation = VisualTransformation.None,
                validator = SignupInfoValidator.Password
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertIsDisplayed()
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
