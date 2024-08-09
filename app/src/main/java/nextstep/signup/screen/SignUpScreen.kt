package nextstep.signup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.core.validation.EmailValidator
import nextstep.signup.core.validation.NameValidator
import nextstep.signup.core.validation.PasswordMatchValidator
import nextstep.signup.core.validation.PasswordValidator
import nextstep.signup.ui.ThemePreviews
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

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
            onUserNameChange = { userName = it },
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onPasswordConfirmChange = { passwordConfirm = it }
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
fun SignUpInputComponent(
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
    val fields = listOf(
        InputFieldModel(
            value = userName,
            onValueChange = onUserNameChange,
            validator = remember { NameValidator() },
            label = { Text(text = stringResource(id = R.string.signup_username)) }
        ),
        InputFieldModel(
            value = email,
            onValueChange = onEmailChange,
            validator = remember { EmailValidator() },
            label = { Text(text = stringResource(id = R.string.signup_email)) }
        ),
        InputFieldModel(
            value = password,
            onValueChange = onPasswordChange,
            validator = remember { PasswordValidator() },
            label = { Text(text = stringResource(id = R.string.signup_password)) },
            visualTransformation = PasswordVisualTransformation()
        ),
        InputFieldModel(
            value = passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            validator = remember(password) { PasswordMatchValidator(password) },
            label = { Text(text = stringResource(id = R.string.signup_password_confirm)) },
            visualTransformation = PasswordVisualTransformation()
        )
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        fields.forEach { validationData ->
            ValidatedSignUpTextField(validationData)
        }
    }
}

@Composable
fun ValidatedSignUpTextField(
    field: InputFieldModel,
    modifier: Modifier = Modifier,
) {
    var error by remember { mutableStateOf<String?>(null) }
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = field.value,
            label = field.label,
            isError = error != null,
            onValueChange = {
                field.onValueChange(it)
                val result = field.validator.validate(it)
                error = if (!result.isValid) result.message else null
            },
            singleLine = true,
            visualTransformation = field.visualTransformation
        )
        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
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
        ValidatedSignUpTextField(
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
        ValidatedSignUpTextField(
            InputFieldModel(
                value = "이지훈입니다.",
                onValueChange = {},
                validator = NameValidator(),
                label = { Text(text = "이름") }
            )
        )
    }
}