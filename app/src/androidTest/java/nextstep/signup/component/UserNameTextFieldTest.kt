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

class UserNameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val tag = "testTag"

    @Before
    fun setup() {
        composeTestRule.setContent {
            val (text, setText) = remember { mutableStateOf("") }
            UserNameTextFiled(
                text = text,
                onValueChange = setText,
                modifier = Modifier.testTag(tag)
            )
        }
    }

    @Test
    fun `이름_라벨이_보여진다`() {
        // given
        val label = "Username"

        // then
        composeTestRule
            .onNodeWithTag(tag)
            .assert(hasText(label))
    }

    @Test
    fun `이름_입력값은_그대로_보여진다`() {
        // given
        val inputName = "이용우"

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(inputName)

        // then
        composeTestRule
            .onNodeWithText(inputName)
            .assertIsDisplayed()
    }

    @Test
    fun `이름이_1자면_에러문구가_보인다`() {
        // given
        val shortName = "김"

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(shortName)

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `이름이_5자를_넘으면_에러문구가_보인다`() {
        // given
        val longName = "김수한무거북이"

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(longName)

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `이름에_숫자나_기호를_포함하면_에러문구가_보인다`() {
        // given
        val hasNumberName = "이용우2"

        composeTestRule
            .onNodeWithTag(tag)
            .performTextInput(hasNumberName)

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertIsDisplayed()
    }

}
