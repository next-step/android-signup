package nextstep.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.EmailTextInputField
import nextstep.signup.ui.component.PasswordConfirmInputField
import nextstep.signup.ui.component.PasswordInputField
import nextstep.signup.ui.component.TextButton
import nextstep.signup.ui.component.UsernameTextInputField

@Composable
fun SignUpScreen() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.signup_title),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        val userName = remember { mutableStateOf("") }
        UsernameTextInputField(userName = userName.value,
            onNameChange = { userName.value = it }
        )

        val email = remember { mutableStateOf("") }
        EmailTextInputField(
            email = email.value,
            onEmailChange = { email.value = it }
        )

        val password = remember { mutableStateOf("") }
        PasswordInputField(
            password = password.value,
            onPasswordChange = { password.value = it },
        )

        val passwordConfirm = remember { mutableStateOf("") }
        PasswordConfirmInputField(
            password = password.value,
            passwordConfirm = passwordConfirm.value,
            onPasswordConfirmChange = { passwordConfirm.value = it }
        )

        TextButton(
            modifier = Modifier.padding(top = 32.dp),
            text = stringResource(id = R.string.signup_button),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
