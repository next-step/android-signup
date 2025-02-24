package nextstep.signup.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.SignUpTextFieldValidation


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        val userNameSupportingMessage by remember {
            derivedStateOf { SignUpTextFieldValidation.getUsernameValidationMessage(username) }
        }
        val emailSupportingMessage by remember {
            derivedStateOf { SignUpTextFieldValidation.getEmailValidationMessage(email) }
        }
        val passwordSupportingMessage by remember {
            derivedStateOf { SignUpTextFieldValidation.getPasswordValidationMessage(password) }
        }
        val passwordConfirmSupportingMessage by remember {
            derivedStateOf { SignUpTextFieldValidation.getPasswordConfirmValidationMessage( password, passwordConfirm) }
        }

        val isValidated = remember(username, email, password, passwordConfirm) {
            SignUpTextFieldValidation.isAllFieldValidated(
                username,
                email,
                password,
                passwordConfirm
            )
        }
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }

        SignUpTitle(Modifier.padding(top = 60.dp))
        UserNameTextField(
            text = username,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onTextChanged = { username = it },
            supportingMessage = userNameSupportingMessage,
            modifier = Modifier.padding(top = 42.dp),
        )
        EmailTextField(
            text = email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onTextChanged = { email = it },
            supportingMessage = emailSupportingMessage,
            modifier = Modifier.padding(top = 33.dp),
        )
        PasswordTextField(
            text = password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onTextChanged = { password = it },
            supportingMessage = passwordSupportingMessage,
            modifier = Modifier.padding(top = 33.dp),
        )

        PasswordConfirmTextField(
            text = passwordConfirm,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onTextChanged = { passwordConfirm = it },
            supportingMessage = passwordConfirmSupportingMessage,
            modifier = Modifier.padding(top = 33.dp),
        )

        SignUpButton(
            isEnabled = isValidated,
            modifier = Modifier.padding(top = 39.dp),
            onClick = {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = "회원가입 성공",
                        actionLabel = "닫기"
                    )
                }
            }
        )

        SnackbarHost(hostState = snackBarHostState)
    }
}