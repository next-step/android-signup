package nextstep.signup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.InputValidation
import nextstep.signup.R
import nextstep.signup.ValidationResult
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmationTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.SignUpSnackbar
import nextstep.signup.ui.component.UserNameTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }

    var usernameValidationResult by remember {
        mutableStateOf(InputValidation.validateUserName(username))
    }
    var emailValidationResult by remember { mutableStateOf(InputValidation.validateEmail(email)) }
    var passwordValidationResult by remember {
        mutableStateOf(InputValidation.validatePassword(password))
    }
    var passwordConfirmationValidationResult by remember {
        mutableStateOf(
            InputValidation.validateConfirmPassword(
                password = password,
                confirmPassword = passwordConfirmation
            )
        )
    }

    val isSignupButtonEnable by remember {
        derivedStateOf {
            usernameValidationResult == ValidationResult.SUCCESS &&
                    emailValidationResult == ValidationResult.SUCCESS &&
                    passwordValidationResult == ValidationResult.SUCCESS &&
                    passwordConfirmationValidationResult == ValidationResult.SUCCESS
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(R.string.signup_success)

    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 76.dp),
            text = stringResource(R.string.header),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        UserNameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            value = username,
            onValueChange = {
                username = it
                usernameValidationResult = InputValidation.validateUserName(username)
            },
            errorMessage = usernameValidationResult.resourceId?.let { stringResource(it) },
            imeAction = ImeAction.Next
        )

        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                emailValidationResult = InputValidation.validateEmail(email)
            },
            errorMessage = emailValidationResult.resourceId?.let { stringResource(it) },
            imeAction = ImeAction.Next
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                passwordValidationResult = InputValidation.validatePassword(password)
            },
            errorMessage = passwordValidationResult.resourceId?.let { stringResource(it) },
            imeAction = ImeAction.Next
        )

        PasswordConfirmationTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordConfirmation,
            onValueChange = {
                passwordConfirmation = it
                passwordConfirmationValidationResult =
                    InputValidation.validateConfirmPassword(
                        password = password,
                        confirmPassword = passwordConfirmation
                    )
            },
            errorMessage = passwordConfirmationValidationResult.resourceId?.let { stringResource(it) },
            imeAction = ImeAction.Done
        )

        SignUpButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 6.dp),
            enabled = isSignupButtonEnable,
            onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(snackbarMessage)
                }
            }
        )
        SignUpSnackbar(
            snackbarHostState = snackbarHostState,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    SignupTheme {
        SignUpScreen()
    }
}