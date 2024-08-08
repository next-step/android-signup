package nextstep.signup.study

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.component.SignUpTextField
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

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
            SignUpTextField(placeholder = placeholder)
        }

        composeTestRule
            .onNodeWithText(placeholder)
            .assertExists()
    }

    @Test
    fun emailTextFieldTest() {
        val placeholder = "Email"

        composeTestRule.setContent {
            SignUpTextField(placeholder = placeholder)
        }

        composeTestRule
            .onNodeWithText(placeholder)
            .assertExists()
    }

    @Test
    fun passwordTextFieldTest() {
        val placeholder = "Password"

        composeTestRule.setContent {
            SignUpTextField(placeholder = placeholder)
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
fun UserNameTextField(placeholder: String) {
    SignUpTextField(placeholder = placeholder)
}

@Composable
fun EmailTextField(placeholder: String) {
    SignUpTextField(placeholder = placeholder)
}


@Composable
fun PasswordTextField(placeholder: String) {
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("user textField"),
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(placeholder) },
        value = text,
        onValueChange = { text = it }
    )
}

@Composable
fun ButtonCompose() {
    Button(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(),
        onClick = {}) {
        Text(
            text = "Sign Up",
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
        )
    }
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
    UserNameTextField("UserName")
}

@Preview
@Composable
fun EmailTextFieldComposePreview() {
    EmailTextField("Email")
}

@Preview
@Composable
fun PasswordTextFieldComposePreview() {
    PasswordTextField("Password")
}

@Preview
@Composable
fun ButtonComposePreview() {
    ButtonCompose()
}