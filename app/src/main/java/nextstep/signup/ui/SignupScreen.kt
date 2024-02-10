package nextstep.signup.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignupScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Welcome to Compose \uD83D\uDE80",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        val username = remember { mutableStateOf("") }
        SignupTextField(
            value = username.value,
            onValueChange = { username.value = it },
            placeholder = "Username",
        )

        val email = remember { mutableStateOf("") }
        SignupTextField(
            value = email.value,
            onValueChange = { email.value = it },
            placeholder = "Email",
        )

        val password = remember { mutableStateOf("") }
        SignupTextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = "Password",
        )

        val passwordConfirm = remember { mutableStateOf("") }
        SignupTextField(
            value = passwordConfirm.value,
            onValueChange = { passwordConfirm.value = it },
            placeholder = "Password Confirm",
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 36.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Sign Up")
        }
    }
}

@Composable
private fun SignupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 36.dp),
        placeholder = { Text(text = placeholder) },
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
