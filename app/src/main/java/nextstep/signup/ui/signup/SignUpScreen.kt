package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.component.ConfirmPasswordValidationResult
import nextstep.signup.ui.component.ConfirmPasswordValidationResult.Companion.validateConfirmPassword
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.EmailValidationResult
import nextstep.signup.ui.component.EmailValidationResult.Companion.validateEmail
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.PasswordValidationResult
import nextstep.signup.ui.component.PasswordValidationResult.Companion.validatePassword
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.UserNameTextField
import nextstep.signup.ui.component.UserNameValidationResult
import nextstep.signup.ui.component.UserNameValidationResult.Companion.validateUserName


@Composable
fun SignUpScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                var username by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var passwordConfirm by remember { mutableStateOf("") }

                val userNameValidationResult = validateUserName(username)
                val isUsernameValid  = remember(username) {
                    userNameValidationResult == UserNameValidationResult.Valid
                }
                val emailValidationResult = validateEmail(email)
                val isEmailValid  = remember(email) {
                    emailValidationResult == EmailValidationResult.Valid
                }
                val passwordValidationResult = validatePassword(password)
                val isPasswordValid = remember(password) {
                    passwordValidationResult == PasswordValidationResult.Valid
                }
                val confirmPasswordValidationResult = validateConfirmPassword(password, passwordConfirm)
                val isPasswordConfirmValid = remember(password, passwordConfirm) {
                    confirmPasswordValidationResult == ConfirmPasswordValidationResult.Valid
                }

                val isButtonValid = remember(isUsernameValid, isEmailValid, isPasswordValid, isPasswordConfirmValid) {
                    isUsernameValid && isEmailValid && isPasswordValid && isPasswordConfirmValid
                }

                WelcomeTitle()
                UserNameTextField(
                    username = username,
                    onNameChange = { username = it },
                    validationResult = userNameValidationResult
                )
                EmailTextField(
                    email = email,
                    onEmailChange = { email = it },
                    validationResult = emailValidationResult
                )
                PasswordTextField(
                    password = password,
                    onPasswordChange = { password = it },
                    validationResult = passwordValidationResult
                )
                PasswordConfirmTextField(
                    confirmPassword = passwordConfirm,
                    onPasswordChange = { passwordConfirm = it },
                    validationResult = confirmPasswordValidationResult
                )
                SignUpButton(
                    onClicked = {
                        if (isButtonValid) {
                            scope.launch {
                                snackbarHostState.showSnackbar("회원가입 성공!")
                            }
                        }
                    },
                    enabled = isButtonValid,
                )
            }
        },
        modifier = Modifier.padding(32.dp, 60.dp)
    )
}

@Composable
private fun WelcomeTitle() {
    Text(
        text = stringResource(id = R.string.sign_up_title),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
