package nextstep.signup.study

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.tooling.preview.Preview
import org.junit.Rule
import org.junit.Test

class ButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text() {
        composeTestRule.setContent {
            MaterialTheme {
                Button(onClick = { /*TODO*/ }) {
                    Text("이름")
                }
            }
        }

        composeTestRule
            .onNodeWithText("이름")
            .assertExists()
    }

    @Test
    fun click() {
        var clicked = false
        composeTestRule.setContent {
            MaterialTheme {
                Button(
                    onClick = { clicked = true },
                    modifier = Modifier.testTag("버튼")
                ) {
                    Text("이름")
                }
            }
        }

        composeTestRule
            .onNodeWithTag("버튼")
            .performClick()

        assert(clicked)
    }
}


@Preview
@Composable
private fun ButtonPreview() {
    MaterialTheme {
        Button(onClick = { /*TODO*/ }) {
            Text("이름")
        }
    }
}
