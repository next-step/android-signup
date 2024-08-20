package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScene()
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(
        style = TextStyle(
            lineHeight = 20.sp,
            letterSpacing = 0.01.em,
            fontSize = 26.sp,
            fontWeight = FontWeight(700),
        ),
        text = text,
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScene() {
    SignupTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(112.dp))
            Greeting("Welcome to Compose \uD83D\uDE80")
            Spacer(Modifier.height(36.dp))
            UserNameView(label = "Username")
            Spacer(Modifier.height(36.dp))
            EmailView("Email")
            Spacer(Modifier.height(36.dp))
            PasswordView("Password")
            Spacer(Modifier.height(36.dp))
            PasswordConfirmView("Password Confirm")
            Spacer(Modifier.height(36.dp))
            SignUpButton("Sign Up")
        }
    }
}

@Composable
fun UserNameView(
    label: String,
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        modifier = Modifier.width(296.dp),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        onValueChange = { text = it },
        label = {
            Text(
                text = label,
            )
        },
    )
}

@Composable
fun EmailView(
    label: String,
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        modifier = Modifier.width(296.dp),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        onValueChange = { text = it },
        label = {
            Text(
                text = label,
            )
        },
    )
}

@Composable
fun PasswordView(
    label: String,
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        modifier = Modifier.width(296.dp),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        onValueChange = { text = it },
        label = {
            Text(
                text = label,
            )
        },
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Composable
fun PasswordConfirmView(
    label: String,
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        modifier = Modifier.width(296.dp),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Send,
            keyboardType = KeyboardType.Password
        ),
        onValueChange = { text = it },
        label = {
            Text(text = label)
        },
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Composable
fun SignUpButton(
    label: String,
) {
    var enabled by remember { mutableStateOf(true) }

    Button(enabled = enabled, colors = ButtonDefaults.buttonColors(
        containerColor = Blue50,
    ), modifier = Modifier
        .width(296.dp)
        .height(50.dp), content = {
        Text(text = label)
    }, onClick = {})
}