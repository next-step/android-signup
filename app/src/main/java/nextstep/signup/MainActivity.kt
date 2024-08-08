package nextstep.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
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
                                Modifier.padding(16.dp),
                                "Username",
                                KeyboardType.Text
                            )
                            TextFieldView(
                                Modifier.padding(16.dp),
                                "Email",
                                KeyboardType.Email
                            )
                            TextFieldView(
                                Modifier.padding(16.dp),
                                "Password",
                                KeyboardType.Password
                            )
                            TextFieldView(
                                Modifier.padding(16.dp),
                                "Password Confirm",
                                KeyboardType.Password
                            )
                            SingUpButtonView(
                                Modifier.padding(16.dp)
                            )
                        }
                    },
                    modifier = Modifier.padding(horizontal = 33.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SingUpButtonView(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
        modifier = modifier
            .fillMaxWidth())
    {
        Text(text = "Sign Up", fontSize = 14.sp, color = Color(0xFFFFFFFF))
    }
}

@Preview
@Composable
private fun TextFieldView(
    modifier: Modifier = Modifier,
    label: String = "UserName",
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val text = remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
        },
        label = {
            Text(
                text = label,
                fontSize = if (isFocused.value) 12.sp else 16.sp,
                color = if (keyboardType == KeyboardType.Text && isFocused.value) {
                    Color(0xFF2196F3)
                } else {
                    Color(0xFF49454F)
                }
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (keyboardType == KeyboardType.Password) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            },
    )
}

@Preview
@Composable
private fun TitleView(
    title: String = "Welcome to Compose \uD83D\uDE80"
) {
    Text(
        text = title,
        fontSize = 26.sp,
        color = Color(0xFF000000),
        fontWeight = FontWeight.Bold
    )
}
