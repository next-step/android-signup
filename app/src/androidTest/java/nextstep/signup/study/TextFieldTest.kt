package nextstep.signup.study

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class TextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text() {
        composeTestRule.setContent {
            BasicTextField(
                value = "김컴포즈",
                onValueChange = { },
            )
        }

        composeTestRule
            .onNodeWithText("김컴포즈")
            .assertExists()
    }

    @Test
    fun input() {
        composeTestRule.setContent {
            val text = remember { mutableStateOf("") }
            BasicTextField(
                value = text.value,
                onValueChange = { text.value = it },
                modifier = Modifier.testTag("이름")
            )
        }

        /**
         * "이름" 태그를 가진 노드에 "윤컴포즈" 텍스트를 입력한다.
         */
        composeTestRule
            .onNodeWithTag("이름")
            .also {
                it.performTextInput("윤컴포즈")
            }
            .assertTextContains("윤컴포즈")
    }
}
