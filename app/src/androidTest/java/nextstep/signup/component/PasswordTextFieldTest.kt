package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val tag = "testTag"

    @Before
    fun setup() {
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf("") }
            PasswordTextFiled(
                text = text,
                onValueChange = setText,
                modifier = Modifier.testTag(tag)
            )
        }
    }

    @Test
    fun `비밀번호_라벨이_보여진다`() {
        //given
        val label = "Password"

        // then
        composeTestRule
            .onNodeWithTag(tag)
            .assert(hasText(label))
    }

    @Test
    fun `비밀번호는_그대로_보이지_않는다`() {
        // given
        val password = "1q2w3e4r"

        //when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(password)

        // then
        composeTestRule
            .onNodeWithText(password)
            .assertIsNotDisplayed()
    }

    @Test
    fun `비밀번호가_8자보다_짧으면_에러문구가_보인다`() {
        // given
        val shortPassword = "1q2w3e"

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(shortPassword)

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호가_16자보다_길면_에러문구가_보인다`() {
        // given
        val longPassword = "1q2w3e4r5t6y7u8i9"

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(longPassword)

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호에_영문과_숫자를_포함하지_않으면_에러문구가_보인다`() {
        // given
        val wrongFormatPassword = "가나다라마바사아"

        //when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(wrongFormatPassword)

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertIsDisplayed()
    }
}
