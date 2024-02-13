package nextstep.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.component.PasswordInputField
import nextstep.signup.ui.component.TextInputField

@Composable
fun SignUpScreen() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Text(
            text = "Welcome to Compose \uD83D\uDE80",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        val userName = remember { mutableStateOf("") }
        TextInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = userName.value,
            label = "UserName",
            onValueChange = { userName.value = it }
        )

        val email = remember { mutableStateOf("") }
        TextInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = email.value,
            label = "Email",
            onValueChange = { email.value = it }
        )

        val password = remember { mutableStateOf("") }
        PasswordInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = password.value,
            label = "Password",
            onValueChange = { password.value = it }
        )

        val passwordConfirm = remember { mutableStateOf("") }
        PasswordInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = passwordConfirm.value,
            label = "Password Confirm",
            onValueChange = { passwordConfirm.value = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}
