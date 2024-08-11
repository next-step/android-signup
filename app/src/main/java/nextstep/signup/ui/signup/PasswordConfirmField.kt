package nextstep.signup.ui.signup

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
fun PasswordConfirmField(
    password: String,
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit
) {
    val validation by remember(passwordConfirm) {
        derivedStateOf { SignUpValidationCheck.isPasswordConfirmValid(password, passwordConfirm) }
    }

    TextField(
        value = passwordConfirm,
        onValueChange = { onPasswordConfirmChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.password_confirm)) },
        supportingText = {
            when (validation) {
                SignUpValidation.INVALID_PASSWORD_MATCH ->
                    Text(text = stringResource(id = R.string.error_invalid_password_confirm))
                else -> {}
            }
        },
        isError = passwordConfirm.isNotBlank() && validation != SignUpValidation.NONE,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmFieldPreview() {
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    PasswordConfirmField(
        password = password,
        passwordConfirm = passwordConfirm,
        onPasswordConfirmChange = { passwordConfirm = it }
    )
}
