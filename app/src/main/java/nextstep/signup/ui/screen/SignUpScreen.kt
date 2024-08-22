package nextstep.signup.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.UsernameTextField
import nextstep.signup.ui.theme.SignupTheme

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    SignupTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            val username = remember { mutableStateOf("") }
            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val passwordConfirm = remember { mutableStateOf("") }

            Spacer(Modifier.height(112.dp))
            SignUpTitle()
            Spacer(Modifier.height(36.dp))
            UsernameTextField(username)
            Spacer(Modifier.height(36.dp))
            EmailTextField(email)
            Spacer(Modifier.height(36.dp))
            PasswordTextField(password)
            Spacer(Modifier.height(36.dp))
            PasswordConfirmTextField(passwordConfirm, passwordProvider = { password.value })
            Spacer(Modifier.height(36.dp))
            SignUpButton("Sign Up")
        }
    }
}

@Composable
private fun SignUpTitle() {
    Text(style = MaterialTheme.typography.titleLarge, text = "Welcome to Compose \uD83D\uDE80")
}