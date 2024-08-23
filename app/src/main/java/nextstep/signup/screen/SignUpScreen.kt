package nextstep.signup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.component.EmailTextField
import nextstep.signup.component.PasswordConfirmTextField
import nextstep.signup.component.PasswordTextField
import nextstep.signup.component.SignUpButton
import nextstep.signup.component.UserNameTextField
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray10
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.util.validation.ValidationResult
import nextstep.signup.util.validation.Validator

enum class SignUpState {
    Pending, Success
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    var signUpstate by rememberSaveable { mutableStateOf(SignUpState.Pending) }

    LaunchedEffect(signUpstate) {
        if (signUpstate == SignUpState.Success) {
            snackBarHostState.showSnackbar(
                context.getString(R.string.sign_up_success)
            )
            signUpstate = SignUpState.Pending
        }
    }

    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirm by rememberSaveable { mutableStateOf("") }

    val userNameValidationResult by remember {
        derivedStateOf { Validator.userNameValidate(userName) }
    }

    val emailValidationResult by remember {
        derivedStateOf { Validator.emailValidate(email) }
    }

    val passwordValidationResult by remember {
        derivedStateOf { Validator.passwordValidate(password) }
    }

    val passwordConfirmValidationResult by remember {
        derivedStateOf {
            Validator.passwordConfirmValidate(
                password = password,
                passwordConfirm = passwordConfirm
            )
        }
    }

    val enabledSignUpButton by remember(
        userNameValidationResult,
        emailValidationResult,
        passwordValidationResult,
        passwordConfirmValidationResult
    ) {
        derivedStateOf {
            userNameValidationResult is ValidationResult.Success &&
                    emailValidationResult is ValidationResult.Success &&
                    passwordValidationResult is ValidationResult.Success &&
                    passwordConfirmValidationResult is ValidationResult.Success
        }
    }

    val scrollState = rememberScrollState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        SignUpScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState),
            userName = userName,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            userNameValidationResult = userNameValidationResult,
            emailValidationResult = emailValidationResult,
            passwordValidationResult = passwordValidationResult,
            passwordConfirmValidationResult = passwordConfirmValidationResult,
            enabledSignUpButton = enabledSignUpButton,
            setUserName = { userName = it },
            setEmail = { email = it },
            setPassword = { password = it },
            setPasswordConfirm = { passwordConfirm = it },
            onClickButton = {
                signUpstate = SignUpState.Success
            }
        )
    }
}


@Composable
fun SignUpScreen(
    userName: String,
    email: String,
    password: String,
    passwordConfirm: String,
    userNameValidationResult: ValidationResult,
    emailValidationResult: ValidationResult,
    passwordValidationResult: ValidationResult,
    passwordConfirmValidationResult: ValidationResult,
    enabledSignUpButton: Boolean,
    setUserName: (String) -> Unit,
    setEmail: (String) -> Unit,
    setPassword: (String) -> Unit,
    setPasswordConfirm: (String) -> Unit,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 60.dp, bottom = 6.dp),
            text = stringResource(id = R.string.sign_up_title),
            style = MaterialTheme.typography.headlineMedium
        )
        UserNameTextField(
            modifier = Modifier.fillMaxWidth(),
            text = userName,
            validationResult = userNameValidationResult,
            onValueChange = setUserName,
        )
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            text = email,
            validationResult = emailValidationResult,
            onValueChange = setEmail
        )
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            text = password,
            validationResult = passwordValidationResult,
            onValueChange = setPassword,
        )
        PasswordConfirmTextField(
            modifier = Modifier.fillMaxWidth(),
            text = passwordConfirm,
            validationResult = passwordConfirmValidationResult,
            onValueChange = setPasswordConfirm,
        )
        SignUpButton(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .height(50.dp),
            enabledSignUpButton = enabledSignUpButton,
            onClickButton = onClickButton
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen(
            userName = "김컴포즈",
            email = "kim@compose.com",
            password = "1234asdf",
            passwordConfirm = "1234asdf",
            userNameValidationResult = Validator.userNameValidate("김컴포즈"),
            emailValidationResult = Validator.emailValidate("kim@compose.com"),
            passwordValidationResult = Validator.passwordValidate("1234asdf"),
            passwordConfirmValidationResult = Validator.passwordConfirmValidate(
                password = "1234asdf",
                passwordConfirm = "1234asdf"
            ),
            enabledSignUpButton = true,
            setUserName = {},
            setEmail = {},
            setPassword = {},
            setPasswordConfirm = {},
            onClickButton = {}
        )
    }
}
