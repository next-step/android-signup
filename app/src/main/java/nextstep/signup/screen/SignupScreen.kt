package nextstep.signup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(42.dp)
    ) {
        SignupTitle(text = stringResource(R.string.signup_title))

        SignupInputComponent(
            userName = userName,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            onUserNameChange = { userName = it },
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onPasswordConfirmChange = { passwordConfirm = it }
        )

        SignupButton(
            onClick = { /* TODO */ }
        )
    }
}

@Composable
private fun SignupTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(top = 42.dp),
        text = text,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold
    )
}


@Composable
private fun SignupInputComponent(
    userName: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        SignupTextField(
            text = userName,
            placeHolder = "UserName",
            label = "UserName",
            onValueChange = onUserNameChange
        )

        SignupTextField(
            text = email,
            placeHolder = "Email",
            label = "Email",
            onValueChange = onEmailChange
        )

        SignupTextField(
            text = password,
            placeHolder = "Password",
            label = "Password",
            onValueChange = onPasswordChange
        )

        SignupTextField(
            text = passwordConfirm,
            placeHolder = "Password Confirm",
            label = "Password Confirm",
            onValueChange = onPasswordConfirmChange
        )
    }
}

@Composable
private fun SignupTextField(
    text: String,
    placeHolder: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
        onValueChange = onValueChange,
        singleLine = true
    )
}

@Composable
fun SignupButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick() }
    ) {
        Text(
            text = "Sign up",
            color = Color.White,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupButtonPreview() {
    SignupTheme {
        SignupButton(onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    SignupTheme {
        SignupScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupTextFieldPreview() {
    SignupTheme {
        SignupTextField(
            text = "이지훈",
            placeHolder = "이름",
            label = "이름",
            onValueChange = {}
        )
    }
}