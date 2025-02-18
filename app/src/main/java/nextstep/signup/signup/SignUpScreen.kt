package nextstep.signup.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
        var isValidated by remember { mutableStateOf(false) }

        isValidated = SignUpTextFieldValidation.isAllFieldValidated(username, email, password, passwordConfirm)

        SignUpTitle(Modifier.padding(top = 60.dp))
        UserNameTextField(
            text = username,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onTextChanged = { username = it },
            modifier = Modifier.padding(top = 42.dp),
        )
        EmailTextField(
            text = email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onTextChanged = { email = it },
            modifier = Modifier.padding(top = 33.dp),
        )
        PasswordTextField(
            text = password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onTextChanged = { password = it },
            modifier = Modifier.padding(top = 33.dp),
        )

        PasswordConfirmTextField(
            text = passwordConfirm,
            password = password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onTextChanged = { passwordConfirm = it },
            modifier = Modifier.padding(top = 33.dp),
        )

        SignUpButton(
            isEnabled = isValidated,
            modifier = Modifier.padding(top = 39.dp)
        )
    }
}