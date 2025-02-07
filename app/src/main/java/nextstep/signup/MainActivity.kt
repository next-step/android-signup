package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignUpTitle(Modifier.padding(top = 60.dp))
        SignUpTextField(SignUpTextFieldType.USERNAME, Modifier.padding(top = 42.dp))
        SignUpTextField(SignUpTextFieldType.EMAIL, Modifier.padding(top = 33.dp))
        SignUpTextField(SignUpTextFieldType.PASSWORD, Modifier.padding(top = 33.dp))
        SignUpTextField(SignUpTextFieldType.PASSWORD_CONFIRM, Modifier.padding(top = 33.dp))
        SignUpButton(Modifier.padding(top = 39.dp))
    }
}

@Composable
fun SignUpTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome_to_compose),
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
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Color(0xFF49454F),
            focusedIndicatorColor = Blue50,
            unfocusedIndicatorColor = Color(0xFF49454F),
            cursorColor = Blue50,
            focusedContainerColor = Color(0xFFE3E8F1),
            unfocusedContainerColor = Color(0xFFE3E8F1),
        ),
        visualTransformation = when (type) {
            SignUpTextFieldType.USERNAME, SignUpTextFieldType.EMAIL -> VisualTransformation.None
            SignUpTextFieldType.PASSWORD, SignUpTextFieldType.PASSWORD_CONFIRM -> PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when (type) {
                SignUpTextFieldType.USERNAME -> KeyboardType.Text
                SignUpTextFieldType.EMAIL -> KeyboardType.Email
                SignUpTextFieldType.PASSWORD, SignUpTextFieldType.PASSWORD_CONFIRM -> KeyboardType.Password
            }
        ),
        modifier = modifier
            .width(296.dp)
            .height(53.dp)
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
    )
}

@Composable
fun SignUpButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
            .width(296.dp)
            .height(50.dp),
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = Color(0xFF2196F3),
            contentColor = Color.White,
            disabledContainerColor = Color(0x1D1B201F),
            disabledContentColor = Color(0x1D1B201F),
        ),
        content = {
            Text(
                text = stringResource(R.string.signup),
                fontSize = 14.sp,
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle()
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextFieldPreview() {
    SignUpTextField(SignUpTextFieldType.USERNAME)
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignUpButton()
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

enum class SignUpTextFieldType(val hint: String) {
    USERNAME("Username"),
    EMAIL("Email"),
    PASSWORD("Password"),
    PASSWORD_CONFIRM("Password Confirm")
}