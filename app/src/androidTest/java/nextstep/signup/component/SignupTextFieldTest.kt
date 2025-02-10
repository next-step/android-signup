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
import nextstep.signup.validator.Validator
import org.junit.Rule
import org.junit.Test

class SignupTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun userName() {
        // given
        val label = "UserName"
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf("") }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Username,
                modifier = Modifier.testTag(label)
            )
        }

        // then
        composeTestRule
            .onNodeWithTag(label)
            .assertExists()
    }

    @Test
    fun inputText() {
        // given
        val label = "UserName"
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf("") }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Username,
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
    fun passwordIsNotDisplay() {
        // given
        val label = "Password"
        composeTestRule.setContent {
            val (password, setPassword) = remember { mutableStateOf("") }
            SignupTextField(
                label = label,
                text = password,
                onValueChange = setPassword,
                visualTransformation = PasswordVisualTransformation(),
                validator = Validator.Password,
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
        val label = "UserName"
        val userName = "이용우"
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf(userName) }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Username
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
        val label = "UserName"
        val userName = "이용우2"
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf(userName) }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Username
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
        val label = "email"
        val email = "raindragonn!gmail.com"

        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf(email) }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Email
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
        val label = "password"
        val password = "123"

        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf(password) }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Password
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
        val label = "password"
        val password = "가나다라마바사아"

        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf(password) }
            SignupTextField(
                label = label,
                text = text,
                onValueChange = setText,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Password
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
        val label = "password"
        val originPassword = "1q2w3e4r5t"
        val confirmPassword = "1q2w3e4r5"

        composeTestRule.setContent {
            val (password, setPassword) = remember { mutableStateOf(originPassword) }
            val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf(confirmPassword) }

            SignupTextField(
                label = label,
                text = password,
                onValueChange = setPassword,
                visualTransformation = VisualTransformation.None,
                validator = Validator.Password
            )

            SignupTextField(
                label = label,
                text = passwordConfirm,
                onValueChange = setPasswordConfirm,
                visualTransformation = VisualTransformation.None,
                validator = Validator.PasswordConfirm { password }
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertIsDisplayed()
    }
}
