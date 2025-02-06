package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignupTextFieldText {
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
                isPassword = false,
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
                isPassword = false,
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
    fun password() {
        // given
        val label = "Password"
        composeTestRule.setContent {
            val (password, setPassword) = remember { mutableStateOf("") }
            SignupTextField(
                label = label,
                text = password,
                onValueChange = setPassword,
                isPassword = true,
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
}
