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
import nextstep.signup.domain.Email
import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.Username
import nextstep.signup.mapper.toUiState
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpScreen(
    username: String,
    isUsernameError: Boolean,
    usernameSupportingText: String,
    onUserNameChange: (String) -> Unit,
    email: String,
    isEmailError: Boolean,
    emailSupportingText: String,
    onEmailChange: (String) -> Unit,
    password: String,
    isPasswordError: Boolean,
    passwordSupportingText: String,
    onPasswordChange: (String) -> Unit,
    passwordConfirm: String,
    isPasswordConfirmError: Boolean,
    passwordConfirmSupportingText: String,
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
            isError = isUsernameError,
            supportingText = usernameSupportingText,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        SignUpTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.sign_up_email_label),
            keyboardType = KeyboardType.Email,
            isError = isEmailError,
            supportingText = emailSupportingText,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        SignUpTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.sign_up_password_label),
            keyboardType = KeyboardType.Password,
            isError = isPasswordError,
            supportingText = passwordSupportingText,
            needHide = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        SignUpTextField(
            value = passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            label = stringResource(R.string.sign_up_password_confirm_label),
            keyboardType = KeyboardType.Password,
            isError = isPasswordConfirmError,
            supportingText = passwordConfirmSupportingText,
            needHide = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(22.dp))
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
        var username by remember { mutableStateOf(Username("김").toUiState()) }
        var email by remember { mutableStateOf(Email("kimcompose@gmail.com").toUiState()) }
        var password by remember { mutableStateOf(Password("12345678").toUiState()) }
        var passwordConfirm by remember {
            mutableStateOf(
                PasswordConfirm("12345678").toUiState(password.password)
            )
        }

        SignUpScreen(
            username = username.username,
            isUsernameError = username.isError,
            usernameSupportingText = username.supportingText,
            onUserNameChange = { username = Username(it).toUiState() },
            email = email.email,
            isEmailError = email.isError,
            emailSupportingText = email.supportingText,
            onEmailChange = { email = Email(it).toUiState() },
            password = password.password,
            isPasswordError = password.isError,
            passwordSupportingText = password.supportingText,
            onPasswordChange = { password = Password(it).toUiState() },
            passwordConfirm = passwordConfirm.passwordConfirm,
            isPasswordConfirmError = passwordConfirm.isError,
            passwordConfirmSupportingText = passwordConfirm.supportingText,
            onPasswordConfirmChange = { passwordConfirm = PasswordConfirm(it).toUiState(password.password) },
            onSignUpButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
