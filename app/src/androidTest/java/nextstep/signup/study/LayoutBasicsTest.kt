package nextstep.signup.study

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.junit.Rule
import org.junit.Test

class LayoutBasicsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text() {
        // given
        val text = "안녕 난 컴포즈야~"
        composeTestRule.setContent {
            MyText(text)
        }

        // then
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
    }

    @Test
    fun column() {
        // given
        composeTestRule.setContent {
            Column(
                modifier = Modifier.testTag("이름")
            ) {
                Text(text = "깜포즈")
                Text(text = "킴포즈")
                Text(text = "끔포즈")
            }
        }

        // then
        composeTestRule.onNode(hasTestTag("이름"))
            .onChildren()
            .assertCountEquals(3)
            .onFirst()
            .assert(hasText("깜포즈"))
        
        composeTestRule.onNodeWithTag("이름")
            .onChildren()
            .assertCountEquals(3)
            .onFirst()
            .assert(hasText("깜포즈"))
    }

    @Test
    fun button() {
        // given
        composeTestRule.setContent {
            MyButton(
                text = "클릭해주세요"
            )
        }

        // when
        val button = composeTestRule
            .onNode(hasTestTag("버튼"))
            .performClick()

        // then
        button.assertIsNotEnabled()
    }
}

@Composable
private fun MyText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        ),
    )
}

@Composable
private fun MyButton(text: String) {
    val enabled = remember { mutableStateOf(true) }
    Button(
        onClick = { enabled.value = !enabled.value },
        enabled = enabled.value,
        modifier = Modifier.testTag("버튼")
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun MyTextPreview() {
    MyText("테스트 텍스트")
}

@Preview(showBackground = true)
@Composable
private fun MyButtonPreview() {
    MyButton("테스트 버튼")
}
