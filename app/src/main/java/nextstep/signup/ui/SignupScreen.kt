package nextstep.signup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.domain.EmailValidationResult
import nextstep.signup.domain.PasswordConfirmValidationResult
import nextstep.signup.domain.PasswordValidationResult
import nextstep.signup.domain.UsernameValidationResult
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UsernameTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignupScreen() {
    var username by remember { mutableStateOf("") }
    val usernameValidationResult = remember(username) {
        UsernameValidationResult.match(username)
    }

    var email by remember { mutableStateOf("") }
    val emailValidationResult = remember(email) {
        EmailValidationResult.match(email)
    }

    var password by remember { mutableStateOf("") }
    val passwordValidationResult = remember(password) {
        PasswordValidationResult.match(password)
    }

    var passwordConfirm by remember { mutableStateOf("") }
    val passwordConfirmValidationResult = remember(password, passwordConfirm) {
        PasswordConfirmValidationResult.match(password, passwordConfirm)
    }

    SignupScreen(
        username = username,
        usernameValidationResult = usernameValidationResult,
        onUsernameChange = { username = it },
        email = email,
        emailValidationResult = emailValidationResult,
        onEmailChange = { email = it },
        password = password,
        passwordValidationResult = passwordValidationResult,
        onPasswordChange = { password = it },
        passwordConfirm = passwordConfirm,
        passwordConfirmValidationResult = passwordConfirmValidationResult,
        onPasswordConfirmChange = { passwordConfirm = it },
        onButtonClick = { TODO() },
    )
}

@Composable
internal fun SignupScreen(
    username: String,
    usernameValidationResult: UsernameValidationResult,
    onUsernameChange: (String) -> Unit,
    email: String,
    emailValidationResult: EmailValidationResult,
    onEmailChange: (String) -> Unit,
    password: String,
    passwordValidationResult: PasswordValidationResult,
    onPasswordChange: (String) -> Unit,
    passwordConfirm: String,
    passwordConfirmValidationResult: PasswordConfirmValidationResult,
    onPasswordConfirmChange: (String) -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        Text(
            text = stringResource(id = R.string.signup_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        UsernameTextField(
            username = username,
            validationResult = usernameValidationResult,
            onNameChange = onUsernameChange,
        )
        EmailTextField(
            email = email,
            validationResult = emailValidationResult,
            onEmailChange = onEmailChange,
        )
        PasswordTextField(
            password = password,
            validationResult = passwordValidationResult,
            onPasswordChange = onPasswordChange,
        )
        PasswordConfirmTextField(
            password = passwordConfirm,
            validationResult = passwordConfirmValidationResult,
            onPasswordChange = onPasswordConfirmChange,
        )

        Button(
            onClick = onButtonClick,
            enabled = remember(
                usernameValidationResult,
                emailValidationResult,
                passwordValidationResult,
                passwordConfirmValidationResult
            ) {
                usernameValidationResult == UsernameValidationResult.SUCCESS
                    && emailValidationResult == EmailValidationResult.SUCCESS
                    && passwordValidationResult == PasswordValidationResult.SUCCESS
                    && passwordConfirmValidationResult == PasswordConfirmValidationResult.SUCCESS
            },
            modifier = Modifier
                .padding(top = 36.dp)
                .fillMaxWidth()
                .testTag("button")
            ,
        ) {
            Text(text = stringResource(id = R.string.signup_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    SignupTheme {
        SignupScreen()
    }
}
