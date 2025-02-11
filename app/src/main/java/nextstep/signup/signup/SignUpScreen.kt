package nextstep.signup.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nextstep.signup.SignUpTextFieldType
import nextstep.signup.SignUpTextFieldValidation


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        var usernameSupportingText by remember { mutableStateOf("") }
        var emailSupportingText by remember { mutableStateOf("") }
        var passwordSupportingText by remember { mutableStateOf("") }
        var passwordConfirmSupportingText by remember { mutableStateOf("") }

        val validation = SignUpTextFieldValidation()

        val onUsernameChanged: (String) -> Unit = {
            username = it
            usernameSupportingText = validation.getUsernameValidationMessage(username)
        }

        val onEmailChanged: (String) -> Unit = {
            email = it
            emailSupportingText = validation.getEmailValidationMessage(email)
        }

        val onPasswordChanged: (String) -> Unit = {
            password = it
            passwordSupportingText =
                validation.getPasswordValidationMessage(password, passwordConfirm)
            passwordConfirmSupportingText =
                validation.getPasswordConfirmValidationMessage(password, passwordConfirm)
        }

        val onPasswordConfirmChanged: (String) -> Unit = {
            passwordConfirm = it
            passwordConfirmSupportingText =
                validation.getPasswordConfirmValidationMessage(password, passwordConfirm)
        }

        SignUpTitle(Modifier.padding(top = 60.dp))
        SignUpTextField(
            text = username,
            type = SignUpTextFieldType.USERNAME,
            modifier = Modifier.padding(top = 42.dp),
            onTextChanged = onUsernameChanged,
            errorMessage = usernameSupportingText
        )
        SignUpTextField(
            text = email,
            type = SignUpTextFieldType.EMAIL,
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onEmailChanged,
            errorMessage = emailSupportingText
        )
        SignUpTextField(
            text = password,
            type = SignUpTextFieldType.PASSWORD,
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onPasswordChanged,
            errorMessage = passwordSupportingText
        )
        SignUpTextField(
            text = passwordConfirm,
            type = SignUpTextFieldType.PASSWORD_CONFIRM,
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onPasswordConfirmChanged,
            errorMessage = passwordConfirmSupportingText
        )
        SignUpButton(Modifier.padding(top = 39.dp))
    }
}