package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.util.SignUpValidationCheck


@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    val validation by remember(password) {
        derivedStateOf { SignUpValidationCheck.validatePassword(password) }
    }

    TextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.password)) },
        supportingText = {
            when (validation) {
                SignUpValidation.INVALID_LENGTH ->
                    Text(text = stringResource(id = R.string.error_invalid_password_length))
                SignUpValidation.INVALID_PASSWORD ->
                    Text(text = stringResource(id = R.string.error_invalid_password))
                else -> {}
            }
        },
        isError = password.isNotBlank() && validation != SignUpValidation.NONE,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordFieldPreview() {
    var emptyPassword by remember { mutableStateOf("") }
    var validPassword by remember { mutableStateOf("abcd1234") }
    var invalidPassword by remember { mutableStateOf("1234567890") }
    Column {
        PasswordField(
            password = emptyPassword,
            onPasswordChange = { emptyPassword = it }
        )
        PasswordField(
            password = validPassword,
            onPasswordChange = { validPassword = it }
        )
        PasswordField(
            password = invalidPassword,
            onPasswordChange = { invalidPassword = it }
        )
    }
}
