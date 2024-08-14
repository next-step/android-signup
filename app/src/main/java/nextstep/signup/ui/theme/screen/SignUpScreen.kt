package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.theme.screen.validator.EmailValidator
import nextstep.signup.ui.theme.screen.validator.NameValidator
import nextstep.signup.ui.theme.screen.validator.PasswordConfirmValidator
import nextstep.signup.ui.theme.screen.validator.PasswordValidator

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUp()
}

@Composable
fun SignUp() {
    val userName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirm = remember { mutableStateOf("") }

    val isNameError = remember { mutableStateOf(true) }
    val isEmailError = remember { mutableStateOf(true) }
    val isPasswordError = remember { mutableStateOf(true) }
    val isPasswordConfirmError = remember { mutableStateOf(true) }

    val isFormValid by remember {
        derivedStateOf {
            !isNameError.value && !isEmailError.value && !isPasswordError.value && !isPasswordConfirmError.value
        }
    }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 62.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Title("Welcome to Compose \uD83D\uDE80")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignUpTextField(
                    stringResource(R.string.username),
                    userName.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    visualTransformation = VisualTransformation.None,
                    NameValidator(),
                    onValueChange = {
                        userName.value = it
                    },
                    onValidChanged = {
                        isNameError.value = it
                    }
                )
                SignUpTextField(
                    stringResource(R.string.email),
                    email.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None,
                    EmailValidator(),
                    onValueChange = {
                        email.value = it
                    },
                    onValidChanged = {
                        isEmailError.value = it
                    }
                )
                SignUpTextField(
                    stringResource(R.string.password),
                    password.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    PasswordValidator(),
                    onValueChange = {
                        password.value = it
                    },
                    onValidChanged = {
                        isPasswordError.value = it
                    }
                )
                SignUpTextField(
                    stringResource(R.string.password_confirm),
                    passwordConfirm.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    PasswordConfirmValidator(password.value),
                    onValueChange = {
                        passwordConfirm.value = it
                    },
                    onValidChanged = {
                        isPasswordConfirmError.value = it
                    }
                )
                SignUpButton(
                    Modifier.padding(16.dp),
                    enabled = isFormValid,
                    onClick = {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.signup_success),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }
        },
        modifier = Modifier.padding(horizontal = 33.dp)
    )
}

