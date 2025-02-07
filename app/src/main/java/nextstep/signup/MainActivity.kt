package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun SignUpTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
        color = Color.Black,
        lineHeight = 20.sp,
        modifier = modifier,
    )
}

@Composable
fun SignUpTextField(
    type: SignUpTextFieldType,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = {
            input = it
        },
        label = { Text(type.hint) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF1D1B20),
            unfocusedTextColor = Color(0xFF1D1B20),
            focusedLabelColor = Color(0xFF2196F3),
            unfocusedLabelColor = Color(0xFF49454F),
            focusedIndicatorColor = Color(0xFF2196F3),
            cursorColor = Color(0xFF2196F3),
            focusedContainerColor = Color(0xFFE3E8F1),
            unfocusedContainerColor = Color(0xFFE3E8F1),
        ),
        visualTransformation = when (type) {
            SignUpTextFieldType.USERNAME -> VisualTransformation.None
            SignUpTextFieldType.EMAIL -> VisualTransformation.None
            SignUpTextFieldType.PASSWORD -> PasswordVisualTransformation()
            SignUpTextFieldType.PASSWORD_CONFIRM -> PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when (type) {
                SignUpTextFieldType.USERNAME -> KeyboardType.Text
                SignUpTextFieldType.EMAIL -> KeyboardType.Email
                SignUpTextFieldType.PASSWORD -> KeyboardType.Password
                SignUpTextFieldType.PASSWORD_CONFIRM -> KeyboardType.Password
            }
        ),
        modifier = modifier
            .width(296.dp)
            .height(53.dp)
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
    )
}


@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle("Welcome to Compose \uD83D\uDE80")
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextFieldPreview() {
    SignUpTextField(SignUpTextFieldType.USERNAME)
}

enum class SignUpTextFieldType(val hint: String) {
    USERNAME("Username"),
    EMAIL("Email"),
    PASSWORD("Password"),
    PASSWORD_CONFIRM("Password Confirm")
}