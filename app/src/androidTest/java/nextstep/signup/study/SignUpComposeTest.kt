package nextstep.signup.study

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.junit.Rule
import org.junit.Test

class SignUpComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleTest() {
        val text = "Welcome to Compose \uD83D\uDE80"

        composeTestRule.setContent {
            TitleCompose(text = text)
        }

        composeTestRule
            .onNodeWithText(text)
            .assertExists()
    }
}

@Composable
fun TitleCompose(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 26.sp,
        color = Color.Black,
        style = TextStyle(
            fontWeight = FontWeight.Bold
        )
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun TitleComposePreview() {
    TitleCompose(text = "Welcome to Compose \uD83D\uDE80")
}
