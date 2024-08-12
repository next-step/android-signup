package nextstep.signup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.core.validation.EmailValidationResult
import nextstep.signup.core.validation.EmailValidator
import nextstep.signup.core.validation.NameValidationResult
import nextstep.signup.core.validation.NameValidator
import nextstep.signup.core.validation.PasswordMatchValidationResult
import nextstep.signup.core.validation.PasswordMatchValidator
import nextstep.signup.core.validation.PasswordValidationResult
import nextstep.signup.core.validation.PasswordValidator
import nextstep.signup.ui.ThemePreviews
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.NameTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    val userNameValidationResult by remember(userName) {
        derivedStateOf { NameValidator().validate(userName) }
    }
    var email by remember { mutableStateOf("") }
    val emailValidationResult by remember(email) {
        derivedStateOf { EmailValidator().validate(email) }
    }
    var password by remember { mutableStateOf("") }
    val passwordValidationResult by remember(password) {
        derivedStateOf { PasswordValidator().validate(password) }
    }
    var passwordConfirm by remember { mutableStateOf("") }
    val passwordMatchValidationResult by remember(passwordConfirm, password) {
        derivedStateOf { PasswordMatchValidator(password).validate(passwordConfirm) }
    }
    val onUserNameChange = remember { { newUseName: String -> userName = newUseName } }
    val onEmailChange = remember { { newEmail: String -> email = newEmail } }
    val onPasswordChange = remember { { newPassword: String -> password = newPassword } }
    val onPasswordMatchChange = remember { { newPasswordConfirm: String -> passwordConfirm = newPasswordConfirm } }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(42.dp)
    ) {
        SignUpTitle(
            modifier = Modifier.padding(top = 42.dp),
            text = stringResource(R.string.signup_title)
        )

        SignUpInputComponent(
            userName = userName,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            userNameValidationResult = userNameValidationResult,
            emailValidationResult = emailValidationResult,
            passwordValidationResult = passwordValidationResult,
            passwordMatchValidationResult = passwordMatchValidationResult,
            onUserNameChange = onUserNameChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onPasswordConfirmChange = onPasswordMatchChange
        )

        SignUpButton(
            onClick = { /* TODO */ }
        )
    }
}

@Composable
private fun SignUpTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}


@Composable
private fun SignUpInputComponent(
    userName: String,
    email: String,
    password: String,
    passwordConfirm: String,
    userNameValidationResult: NameValidationResult,
    emailValidationResult: EmailValidationResult,
    passwordValidationResult: PasswordValidationResult,
    passwordMatchValidationResult: PasswordMatchValidationResult,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        NameTextField(
            userName = userName,
            nameValidationResult = userNameValidationResult,
            onUserNameChange = onUserNameChange
        )
        EmailTextField(
            email = email,
            emailValidationResult = emailValidationResult,
            onEmailChange = onEmailChange
        )
        PasswordTextField(
            password = password,
            passwordValidationResult = passwordValidationResult,
            onPasswordChange = onPasswordChange,
        )
        PasswordConfirmTextField(
            passwordConfirmValue = passwordConfirm,
            passwordMatchValidationResult = passwordMatchValidationResult,
            onPasswordConfirmChange = onPasswordConfirmChange
        )
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick() }
    ) {
        Text(
            text = stringResource(R.string.signup),
            color = Color.White,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@ThemePreviews
@Composable
private fun SignUpTitlePreview() {
    SignupTheme {
        SignUpTitle("Welcome to Compose \uD83D\uDE80")
    }
}

@ThemePreviews
@Composable
private fun SignUpButtonPreview() {
    SignupTheme {
        SignUpButton(onClick = {})
    }
}

@ThemePreviews
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}
