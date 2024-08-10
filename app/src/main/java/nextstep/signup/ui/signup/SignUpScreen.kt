package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(42.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordConfirm by remember { mutableStateOf("") }

            SignUpTitle()
            SignUpInputs(
                username = username,
                email = email,
                password = password,
                passwordConfirm = passwordConfirm,
                onUsernameChange = { username = it },
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onPasswordConfirmChange = { passwordConfirm = it }
            )
            SignUpButton(
                onSignUpClick = { }
            )
        }
    }
}

@Composable
fun SignUpTitle() {
    Text(
        text = "Welcome to Compose \uD83D\uDE80",
        modifier = Modifier.padding(top = 64.dp),
        color = Color.Black,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.01.em,
        lineHeight = 20.sp,
    )
}

@Composable
fun SignUpInputs(
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        UsernameField(
            username = username,
            onUsernameChange = onUsernameChange
        )
        EmailField(
            email = email,
            onEmailChange = onEmailChange
        )
        PasswordField(
            password = password,
            onPasswordChange = onPasswordChange
        )
        PasswordConfirmField(
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChange = onPasswordConfirmChange
        )
    }
}

@Composable
fun UsernameField(
    username: String,
    onUsernameChange: (String) -> Unit
) {
    TextField(
        value = username,
        onValueChange = { onUsernameChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Username") },
        placeholder = { Text(text = "Username") },
    )
}

@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    TextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email") },
        placeholder = { Text(text = "Email") },
    )
}

@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    TextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Password") },
        placeholder = { Text(text = "Password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun PasswordConfirmField(
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit
) {
    TextField(
        value = passwordConfirm,
        onValueChange = { onPasswordConfirmChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "PasswordConfirm") },
        placeholder = { Text(text = "PasswordConfirm") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun SignUpButton(
    onSignUpClick: () -> Unit
) {
    Button(
        onClick = { onSignUpClick.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2196F3)
        )
    ) {
        Text(
            text = "Sign Up",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignUpButton(
        onSignUpClick = { }
    )
}

@Preview(
    showBackground = true,
    widthDp = 400,
    heightDp = 600
)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle()
}

@Preview(showBackground = true)
@Composable
private fun UsernameFieldPreview() {
    var username by remember { mutableStateOf("") }
    UsernameField(
        username = username,
        onUsernameChange = { username = it }
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailFieldPreview() {
    var email by remember { mutableStateOf("") }
    EmailField(
        email = email,
        onEmailChange = { email = it }
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordFieldPreview() {
    var password by remember { mutableStateOf("") }
    PasswordField(
        password = password,
        onPasswordChange = { password = it }
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmFieldPreview() {
    var passwordConfirm by remember { mutableStateOf("") }
    PasswordConfirmField(
        passwordConfirm = passwordConfirm,
        onPasswordConfirmChange = { passwordConfirm = it }
    )
}