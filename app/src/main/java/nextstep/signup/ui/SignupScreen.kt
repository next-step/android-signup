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

        UsernameTextField()
        EmailTextField()
        PasswordTextField()
        PasswordConfirmTextField()

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
internal fun UsernameTextField() {
    val username = remember { mutableStateOf("") }
    TextField(
        value = username.value,
        onValueChange = { username.value = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_username)) },
        singleLine = true,
    )
}

@Composable
internal fun EmailTextField() {
    val email = remember { mutableStateOf("") }
    TextField(
        value = email.value,
        onValueChange = { email.value = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_email)) },
        singleLine = true,
    )
}

@Composable
internal fun PasswordTextField() {
    val password = remember { mutableStateOf("") }
    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_password)) },
        singleLine = true,
    )
}

@Composable
internal fun PasswordConfirmTextField() {
    val passwordConfirm = remember { mutableStateOf("") }
    TextField(
        value = passwordConfirm.value,
        onValueChange = { passwordConfirm.value = it },
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
