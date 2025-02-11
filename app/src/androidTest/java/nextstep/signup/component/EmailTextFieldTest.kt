package nextstep.signup.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val tag = "testTag"

    @Before
    fun setup() {
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf("") }
            EmailTextFiled(
                text = text,
                onValueChange = setText,
                modifier = Modifier.testTag(tag)
            )
        }
    }

    @Test
    fun `이메일_라벨이_보여진다`() {
        //given
        val label = "Email"

        // then
        composeTestRule
            .onNodeWithTag(tag)
            .assert(hasText(label))
    }

    @Test
    fun `이메일_입력값이_보여진다`() {
        // given
        val email = "raindragonn@gmail.com"

        //when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(email)

        // then
        composeTestRule
            .onNodeWithTag(tag)
            .assert(hasText(email))
    }

    @Test
    fun `이메일_형식이_맞지않으면_에러문구가_보인다`() {
        // given
        val wrongEmail = "raindragonn!gmail.com"

        //when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(wrongEmail)

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertIsDisplayed()
    }
}
