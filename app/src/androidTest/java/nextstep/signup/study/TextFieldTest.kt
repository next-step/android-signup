package nextstep.signup.study

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.tooling.preview.Preview
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

    @Test
    fun placeholder() {
        /**
         * value 값이 비어있을 때 placeholder가 출력되지 않을까?
         */
        composeTestRule.setContent {
            TextField(
                value = "",
                onValueChange = { },
                placeholder = {
                    Text("힌트", modifier = Modifier.testTag("1번"))
                },
            )

            TextField(
                value = "이름",
                onValueChange = { },
                placeholder = {
                    Text("힌트", modifier = Modifier.testTag("2번"))
                },
            )
        }

        /**
         * useUnmergedTree 파라미터를 지정하면 자식 컴포저블을 모두 찾는다.
         */
        composeTestRule
            .onNodeWithTag("1번", useUnmergedTree = true)
            .assertExists()

        composeTestRule
            .onNodeWithTag("2번", useUnmergedTree = true)
            .assertDoesNotExist()
    }
}

@Preview
@Composable
private fun TextFieldPreview() {
    MaterialTheme {
        TextField(
            value = "이름",
            onValueChange = { },
            placeholder = {
                Text("힌트")
            },
            modifier = Modifier.testTag("2번")
        )
    }
}
