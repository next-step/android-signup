package nextstep.signup.study

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsNotEnabled
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

// 1. 모든 테스트가 성공하도록 만들어보자
// 2. 힌트를 참고하여 Preview를 노출시킨다.
// 3. Preview의 interactive 모드를 활용하여 버튼을 클릭해본다.
class LayoutBasicsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text() {
        // given
        val text = "안녕 난 컴포즈야~"
        composeTestRule.setContent {
            TextComponent(text)
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
            ColumnComponent()
        }

        // then
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
            ButtonComponent()
        }

        // when
        val button = composeTestRule
            .onNodeWithTag("버튼")
            .performClick()

        // then
        button.assertIsNotEnabled()
    }
}

@Composable
private fun TextComponent(text: String) {
    Text(
        text = text,
        color = Color.Blue,
        style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        ),
    )
}

@Composable
fun ColumnComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.testTag("이름")
    ) {
        Text(text = "깜포즈", color = Color.Red)
        Text(text = "킴포즈", color = Color.Cyan)
        Text(text = "끔포즈", color = Color.Yellow)
    }
}

@Composable
fun ButtonComponent(modifier: Modifier = Modifier) {
    val enabled = remember { mutableStateOf(true) }
    Button(
        onClick = {
            enabled.value = false
        },
        enabled = enabled.value,
        modifier = Modifier.testTag("버튼")
    ) {
        Text(text = "클릭해주세요")
    }
}

@Preview
@Composable
private fun TextComponentPreview() {
    TextComponent(text = "안녕 난 컴포즈야~")
}

@Preview
@Composable
private fun ColumnComponentPreview() {
    ColumnComponent()
}

@Preview
@Composable
private fun ButtonComponentPreview() {
    ButtonComponent()
}
