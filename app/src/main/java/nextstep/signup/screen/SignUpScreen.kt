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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.core.validation.EmailValidator
import nextstep.signup.core.validation.NameValidator
import nextstep.signup.core.validation.PasswordMatchValidator
import nextstep.signup.core.validation.PasswordValidator
import nextstep.signup.ui.ThemePreviews
import nextstep.signup.ui.component.InputFieldModel
import nextstep.signup.ui.component.ValidatedTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
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
            onUserNameChange = onUserNameChange
        )
        EmailTextField(
            email = email,
            onEmailChange = onEmailChange
        )
        PasswordTextField(
            password = password,
            onPasswordChange = onPasswordChange,
        )
        PasswordConfirmTextField(
            password = password,
            passwordConfirmValue = passwordConfirm,
            onPasswordConfirmChange = onPasswordConfirmChange
        )
    }
}

@Composable
fun NameTextField(
    userName: String,
    onUserNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val validator = remember { NameValidator() }
    ValidatedTextField(
        modifier = modifier,
        field = InputFieldModel(
            value = userName,
            onValueChange = onUserNameChange,
            validator = validator,
            label = { Text(text = stringResource(id = R.string.signup_username)) }
        )
    )
}

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ValidatedTextField(
        modifier = modifier,
        field = InputFieldModel(
            value = email,
            onValueChange = onEmailChange,
            validator = remember { EmailValidator() },
            label = { Text(text = stringResource(id = R.string.signup_email)) }
        )
    )
}

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val validator = remember { PasswordValidator() }
    ValidatedTextField(
        modifier = modifier,
        field = InputFieldModel(
            value = password,
            onValueChange = onPasswordChange,
            validator = validator,
            label = { Text(text = stringResource(id = R.string.signup_password)) },
            visualTransformation = PasswordVisualTransformation()
        )
    )
}

@Composable
fun PasswordConfirmTextField(
    password: String,
    passwordConfirmValue: String,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val validator by remember(password) {
        derivedStateOf { PasswordMatchValidator(password) }
    }

    ValidatedTextField(
        modifier = modifier,
        field = InputFieldModel(
            value = passwordConfirmValue,
            onValueChange = onPasswordConfirmChange,
            validator = validator,
            label = { Text(text = stringResource(id = R.string.signup_password_confirm)) },
            visualTransformation = PasswordVisualTransformation()
        )
    )
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
            text = stringResource(R.string.sign_up),
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

@ThemePreviews
@Composable
private fun SignUpTextFieldPreview() {
    SignupTheme {
        ValidatedTextField(
            InputFieldModel(
                value = "이지훈",
                onValueChange = {},
                validator = NameValidator(),
                label = { Text(text = "이름") }
            )
        )
    }
}

@ThemePreviews
@Composable
private fun SignUpTextFieldErrorPreview() {
    SignupTheme {
        ValidatedTextField(
            InputFieldModel(
                value = "이지훈입니다.",
                onValueChange = {},
                validator = NameValidator(),
                label = { Text(text = "이름") }
            )
        )
    }
}