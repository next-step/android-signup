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
import nextstep.signup.ui.component.PasswordInputField
import nextstep.signup.ui.component.TextButton
import nextstep.signup.ui.component.TextInputField

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
        TextInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = userName.value,
            label = stringResource(id = R.string.signup_username),
            onValueChange = { userName.value = it }
        )

        val email = remember { mutableStateOf("") }
        TextInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = email.value,
            label = stringResource(id = R.string.signup_email),
            onValueChange = { email.value = it }
        )

        val password = remember { mutableStateOf("") }
        PasswordInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = password.value,
            label = stringResource(id = R.string.signup_password),
            onValueChange = { password.value = it }
        )

        val passwordConfirm = remember { mutableStateOf("") }
        PasswordInputField(
            modifier = Modifier.padding(top = 32.dp),
            value = passwordConfirm.value,
            label = stringResource(id = R.string.signup_password_confirm),
            onValueChange = { passwordConfirm.value = it }
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
fun SignUpScreenPreview() {
    SignUpScreen()
}
