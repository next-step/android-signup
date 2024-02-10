package nextstep.signup.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignupScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        Text(
            text = stringResource(id = R.string.signup_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        val username = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordConfirm = remember { mutableStateOf("") }

        UsernameTextField(
            username = username.value,
            onNameChange = { username.value = it }
        )
        EmailTextField(
            email = email.value,
            onEmailChange = { email.value = it }
        )
        PasswordTextField(
            password = password.value,
            onPasswordChange = { password.value = it }
        )
        PasswordConfirmTextField(
            password = passwordConfirm.value,
            onPasswordChange = { passwordConfirm.value = it }
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 36.dp)
                .fillMaxWidth(),
        ) {
            Text(text = stringResource(id = R.string.signup_button))
        }
    }
}

@Composable
internal fun UsernameTextField(
    username: String,
    onNameChange: (String) -> Unit,
) {
    TextField(
        value = username,
        onValueChange = onNameChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_username)) },
        singleLine = true,
    )
}

@Composable
internal fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_email)) },
        singleLine = true,
    )
}

@Composable
internal fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_password)) },
        singleLine = true,
    )
}

@Composable
internal fun PasswordConfirmTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_password_confirm)) },
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    SignupTheme {
        SignupScreen()
    }
}
