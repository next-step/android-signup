package nextstep.signup.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.common.component.SignupSnackBar
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.SignupValidator.Email
import nextstep.signup.ui.signup.SignupValidator.Password
import nextstep.signup.ui.signup.SignupValidator.PasswordConfirm
import nextstep.signup.ui.signup.SignupValidator.Username
import nextstep.signup.ui.signup.component.CreateAccountButton
import nextstep.signup.ui.signup.component.EmailTextField
import nextstep.signup.ui.signup.component.HeaderText
import nextstep.signup.ui.signup.component.PasswordConfirmTextField
import nextstep.signup.ui.signup.component.PasswordTextField
import nextstep.signup.ui.signup.component.UsernameTextField

@Composable
fun SignupScreen() {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SignupSnackBar(snackBarHostState) },
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(contentPadding)
                .padding(horizontal = 20.dp),
        ) {
            val (userName, setUserName) = remember { mutableStateOf("") }
            val (email, setEmail) = remember { mutableStateOf("") }
            val (password, setPassword) = remember { mutableStateOf("") }
            val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }
            val usernameValidation = userName.isValid<Username>()
            val emailValidation = email.isValid<Email>()
            val passwordValidation = password.isValid<Password>()
            val passwordConfirmValidation = passwordConfirm.isValid<PasswordConfirm>(password)
            val isValidOfSignup = usernameValidation == Success && emailValidation == Success
                    && passwordValidation == Success && passwordConfirmValidation == Success
            val snackBarMessage = stringResource(id = R.string.signup_success)

            Spacer(modifier = Modifier.height(60.dp))
            HeaderText()
            Spacer(modifier = Modifier.height(42.dp))
            UsernameTextField(
                username = userName,
                onUsernameChanged = setUserName,
                usernameValidation = usernameValidation,
            )
            Spacer(modifier = Modifier.height(36.dp))
            EmailTextField(
                email = email,
                onEmailChanged = setEmail,
                emailValidation = emailValidation,
            )
            Spacer(modifier = Modifier.height(36.dp))
            PasswordTextField(
                password = password,
                onPasswordChanged = setPassword,
                passwordValidation = passwordValidation,
            )
            Spacer(modifier = Modifier.height(36.dp))
            PasswordConfirmTextField(
                passwordConfirm = passwordConfirm,
                onPasswordConfirmChanged = setPasswordConfirm,
                passwordConfirmValidation = passwordConfirmValidation,
            )
            Spacer(modifier = Modifier.height(42.dp))
            CreateAccountButton(
                isSuccessfulCondition = isValidOfSignup,
                onCreateAccountButtonClick = {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = snackBarMessage,
                            duration = SnackbarDuration.Short,
                        )
                    }
                },
            )
        }
    }
}

@Preview
@Composable
private fun SignupScreenPreview() {
    SignupScreen()
}
