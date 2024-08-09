package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.NSTextField
import nextstep.signup.ui.theme.Blue50

private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 60.dp),
        )

        NSTextField(
            value = username,
            onValueChange = { username = it },
            labelValue = stringResource(id = R.string.username),
            supportingTextValue = getUsernameSupportingText(username),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        NSTextField(
            value = email,
            onValueChange = { email = it },
            labelValue = stringResource(id = R.string.email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            supportingTextValue = getEmailSupportingText(email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        NSTextField(
            value = password,
            onValueChange = { password = it },
            labelValue = stringResource(id = R.string.password),
            visualTransformation = PasswordVisualTransformation(),
            supportingTextValue = getPasswordSupportingText(password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        NSTextField(
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            labelValue = stringResource(id = R.string.password_confirm),
            visualTransformation = PasswordVisualTransformation(),
            supportingTextValue = getPasswordConfirmSupportingText(password, passwordConfirm),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        Button(
            onClick = { }, // todo: need to implement
            colors = ButtonDefaults.buttonColors(containerColor = Blue50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(50.dp),
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }
}

@Composable
fun getUsernameSupportingText(username: String): String? {
    if (username.length < 2 || username.length > 5) {
        return stringResource(id = R.string.username_length_error)
    }

    if (!username.matches(Regex(USERNAME_REGEX))) {
        return stringResource(id = R.string.username_regex_error)
    }

    return null
}

@Composable
fun getEmailSupportingText(email: String): String? {
    if (!email.matches(Regex(EMAIL_REGEX))) {
        return stringResource(id = R.string.email_regex_error)
    }

    return null
}

@Composable
fun getPasswordSupportingText(password: String): String? {
    if (password.length < 8 || password.length > 16) {
        return stringResource(id = R.string.password_length_error)
    }
    if (!password.matches(Regex(PASSWORD_REGEX))) {
        return stringResource(id = R.string.password_regex_error)
    }

    return null
}

@Composable
fun getPasswordConfirmSupportingText(password: String, passwordConfirm: String): String? {
    if (password != passwordConfirm) {
        return stringResource(id = R.string.password_confirm_equal_error)
    }
    return null
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}