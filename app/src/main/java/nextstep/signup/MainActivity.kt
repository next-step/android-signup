package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    private val userName = mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                SingUpView()
            }
        }
    }
}

@Preview
@Composable
private fun SingUpView() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 62.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TitleView("Welcome to Compose \uD83D\uDE80")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldView(
                    "Username",
                    KeyboardType.Text
                )
                TextFieldView(
                    "Email",
                    KeyboardType.Email
                )
                TextFieldView(
                    "Password",
                    KeyboardType.Password,
                    PasswordVisualTransformation()
                )
                TextFieldView(
                    "Password Confirm",
                    KeyboardType.Password,
                    PasswordVisualTransformation()
                )
                SingUpButtonView(
                    Modifier.padding(16.dp)
                )
            }
        },
        modifier = Modifier.padding(horizontal = 33.dp)
    )
}

@Preview
@Composable
private fun SingUpButtonPreView() {
    SingUpButtonView(Modifier)
}

@Composable
private fun SingUpButtonView(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = "Sign Up", fontSize = 14.sp, color = Color(0xFFFFFFFF))
    }
}

@Preview
@Composable
private fun TextFieldPreview() {
    TextFieldView("Username", KeyboardType.Text)
}

@Composable
fun TextFieldView(
    label: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    inputString: MutableState<String> = mutableStateOf("")
) {
    val text = remember { inputString }
    val isFocused = remember { mutableStateOf(false) }
    val supportingText by remember(text, keyboardType) {
        derivedStateOf { getErrorMessage(text.value, keyboardType) }
    }
    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
        },
        label = {
            Text(
                text = label,
                fontSize = if (isFocused.value) 12.sp else 16.sp,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color(0xFF2196F3),
            unfocusedLabelColor = Color(0xFF49454F),
            errorIndicatorColor = Color(0xFFB3261E),
            errorLabelColor = Color(0xFFB3261E),
        ),
        isError = supportingText.isNotEmpty(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            },
        supportingText = {
            Text(
                text = supportingText,
                color = Color(0xFFB3261E),
                fontSize = 12.sp
            )
        }
    )
}

private fun getErrorMessage(
    input: String,
    keyboardType: KeyboardType
): String {
    return when (keyboardType) {
        KeyboardType.Text -> {
            if (input.length in 2..5) {
                return ""
            } else {
                "이름은 2~5자여야 합니다."
            }
        } else -> {
            ""
        }
    }
}

@Preview
@Composable
private fun TitlePreView() {
    TitleView("Welcome to Compose \uD83D\uDE80")
}

@Composable
private fun TitleView(
    title: String
) {
    Text(
        text = title,
        fontSize = 26.sp,
        color = Color(0xFF000000),
        fontWeight = FontWeight.Bold
    )
}
