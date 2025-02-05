package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.components.SignUpButton
import nextstep.signup.components.SignUpTextField
import nextstep.signup.components.SignUpTitle
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpScreen(
    username: String,
    onUserNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(60.dp))
        SignUpTitle(stringResource(R.string.sign_up_title))
        Spacer(Modifier.height(42.dp))
        SignUpTextField(
            value = username,
            onValueChange = onUserNameChange,
            label = stringResource(R.string.sign_up_username_label),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(36.dp))
        SignUpTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.sign_up_email_label),
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(36.dp))
        SignUpTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.sign_up_password_label),
            keyboardType = KeyboardType.Password,
            needHide = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(36.dp))
        SignUpTextField(
            value = passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            label = stringResource(R.string.sign_up_password_confirm_label),
            keyboardType = KeyboardType.Password,
            needHide = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(42.dp))
        SignUpButton(
            text = stringResource(R.string.sign_up_sign_up_button_text),
            onClick = onSignUpButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        var username by remember { mutableStateOf("김컴포즈") }
        var email by remember { mutableStateOf("kimcompose@gmail.com") }
        var password by remember { mutableStateOf("12345678") }
        var passwordConfirm by remember { mutableStateOf("12345678") }

        SignUpScreen(
            username = username,
            onUserNameChange = { username = it },
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChange = { passwordConfirm = it },
            onSignUpButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
