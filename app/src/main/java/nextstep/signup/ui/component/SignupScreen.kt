package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.EMAIL
import nextstep.signup.ui.PASSWORD
import nextstep.signup.ui.PASSWORD_CONFIRM
import nextstep.signup.ui.USER_NAME
import nextstep.signup.ui.component.textfield.EmailTextField
import nextstep.signup.ui.component.textfield.PasswordTextField
import nextstep.signup.ui.component.textfield.UsernameTextField
import nextstep.signup.ui.util.EmailValidator
import nextstep.signup.ui.util.NameValidator
import nextstep.signup.ui.util.PasswordMatchValidator
import nextstep.signup.ui.util.PasswordValidator
import nextstep.signup.ui.util.ValidationResult

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .padding(horizontal = 32.dp)
    ) {
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }
        val keyboardController = LocalSoftwareKeyboardController.current

        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        val validationResults = mapOf(
            USER_NAME to NameValidator.validate(username),
            EMAIL to EmailValidator.validate(email),
            PASSWORD to PasswordValidator.validate(password),
            PASSWORD_CONFIRM to PasswordMatchValidator.validate(password, passwordConfirm)
        )

        SignupTitle()
        UsernameTextField(
            inputValue = username,
            onInputChange = { username = it },
            validResult = validationResults.getOrElse(USER_NAME) { ValidationResult.Correct },
        )
        EmailTextField(
            inputValue = email,
            onInputChange = { email = it },
            validResult = validationResults.getOrElse(EMAIL) { ValidationResult.Correct },
        )
        PasswordTextField(
            label = stringResource(R.string.signup_field_label_password),
            inputValue = password,
            onInputChange = { password = it },
            validResult = validationResults.getOrElse(PASSWORD) { ValidationResult.Correct },
        )
        PasswordTextField(
            label = stringResource(R.string.signup_field_label_password_confirm),
            inputValue = passwordConfirm,
            onInputChange = { passwordConfirm = it },
            validResult = validationResults.getOrElse(PASSWORD_CONFIRM) { ValidationResult.Correct },
        )
        val message = stringResource(R.string.signup_complete_snackbar_message)

        SubmitButton(
            enabled = validationResults.values.all { it == ValidationResult.Correct },
            onClick = {
                keyboardController?.hide()

                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = message,
                    )
                }
            }
        )
        SnackbarHost(
            hostState = snackBarHostState,
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    actionColor = Color.Red
                )
            }
        )
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}