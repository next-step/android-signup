package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `비밀번호_확인_라벨이_보여진다`() {
        // given
        val label = "Password Confirm"

        // when
        composeTestRule.setContent {
            val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

            PasswordConfirmTextFiled(
                text = passwordConfirm,
                password = passwordConfirm,
                onValueChange = setPasswordConfirm,
            )
        }

        // then
        composeTestRule
            .onNodeWithText(label)
            .isDisplayed()
    }

    @Test
    fun `비밀번호_확인은_평문으로_보이지_않는다`() {
        composeTestRule.setContent {
            PasswordConfirmTextFiled(
                text = "1q2w3e4r",
                password = "1q2w3e4r",
                onValueChange = { },
            )
        }

        hasSetTextAction()
        // then
        composeTestRule
            .onAllNodesWithText("\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022")
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호와_비밀번호_확인값이_다르면_에러문구가_보인다`() {
        // given
        val passwordTag = "password"
        val confirmTag = "passwordConfirm"
        val originPassword = "1q2w3e4r5t"
        val confirmPassword = "1q2w3e4r5"

        composeTestRule.setContent {
            val (password, setPassword) = remember { mutableStateOf("") }
            val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

            PasswordTextFiled(
                text = password,
                onValueChange = setPassword,
                modifier = Modifier.testTag(passwordTag)
            )

            PasswordConfirmTextFiled(
                text = passwordConfirm,
                password = password,
                onValueChange = setPasswordConfirm,
                modifier = Modifier.testTag(confirmTag)
            )
        }

        // when
        composeTestRule
            .onNodeWithTag(passwordTag)
            .performTextInput(originPassword)

        composeTestRule
            .onNodeWithTag(confirmTag)
            .performTextInput(confirmPassword)

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertIsDisplayed()
    }
}
