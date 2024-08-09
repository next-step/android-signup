package nextstep.signup.screen

import androidx.annotation.VisibleForTesting
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.ThemePreviews
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(
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
        SignUpTitle(
            modifier = Modifier.padding(top = 42.dp),
            text = stringResource(R.string.signup_title)
        )

        SignUpInputComponent(
            userName = userName,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            onUserNameChange = { userName = it },
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onPasswordConfirmChange = { passwordConfirm = it }
        )

        SignUpButton(
            onClick = { /* TODO */ }
        )
    }
}

@Composable
private fun SignUpTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}


@Composable
private fun SignUpInputComponent(
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
        SignUpTextField(
            text = userName,
            label = { Text(text = stringResource(R.string.signup_username)) },
            onValueChange = onUserNameChange
        )

        SignUpTextField(
            text = email,
            label = { Text(text = stringResource(R.string.signup_email)) },
            onValueChange = onEmailChange
        )

        SignUpTextField(
            text = password,
            label = { Text(text = stringResource(R.string.signup_password)) },
            onValueChange = onPasswordChange
        )

        SignUpTextField(
            text = passwordConfirm,
            label = { Text(text = stringResource(R.string.signup_password_confirm)) },
            onValueChange = onPasswordConfirmChange
        )
    }
}

@Composable
fun SignUpTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        label = label,
        onValueChange = onValueChange,
        singleLine = true
    )
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick() }
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            color = Color.White,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@ThemePreviews
@Composable
private fun SignUpTitlePreview() {
    SignupTheme {
        SignUpTitle("Welcome to Compose \uD83D\uDE80")
    }
}

@ThemePreviews
@Composable
private fun SignUpButtonPreview() {
    SignupTheme {
        SignUpButton(onClick = {})
    }
}

@ThemePreviews
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}

@ThemePreviews
@Composable
private fun SignUpTextFieldPreview() {
    SignupTheme {
        SignUpTextField(
            text = "이지훈",
            label = { Text(text = "이름") },
            onValueChange = {}
        )
    }
}