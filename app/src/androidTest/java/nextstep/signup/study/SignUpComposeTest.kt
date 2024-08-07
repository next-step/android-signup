package nextstep.signup.study

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.component.TextFieldCompose
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

    @Test
    fun userNameTextFieldTest() {
        val placeholder = "UserName"

        composeTestRule.setContent {
            TextFieldCompose(placeholder = placeholder)
        }

        composeTestRule
            .onNodeWithText(placeholder)
            .assertExists()
    }

    @Test
    fun emailTextFieldTest() {
        val placeholder = "Email"

        composeTestRule.setContent {
            TextFieldCompose(placeholder = placeholder)
        }

        composeTestRule
            .onNodeWithText(placeholder)
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

@Composable
fun PasswordTextField(placeholder: String) {
    var text by remember { mutableStateOf("123") }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("user textField"),
        visualTransformation = PasswordVisualTransformation(),
        placeholder = { Text(placeholder) },
        value = text,
        onValueChange = { text = it }
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

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun UserNameTextFieldComposePreview() {
    TextFieldCompose("UserName")
}

@Preview
@Composable
fun EmailTextFieldComposePreview() {
    TextFieldCompose("Email")
}

@Preview
@Composable
fun PasswordTextFieldComposePreview() {
    PasswordTextField("Password")
}