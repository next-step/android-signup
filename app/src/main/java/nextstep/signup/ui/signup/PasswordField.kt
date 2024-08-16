package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.util.SignUpValidationCheck


@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    onPasswordValidationSuccess: (Boolean) -> Unit
) {
    val validation by remember(password) {
        derivedStateOf { SignUpValidationCheck.validatePassword(password) }
    }
    LaunchedEffect(validation) {
        onPasswordValidationSuccess(password.isNotBlank() && validation == SignUpValidation.NONE)
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
private fun PasswordFieldPreview(
    @PreviewParameter(PasswordPreviewParameterProvider::class) passwordValue: String
) {
    var password by remember { mutableStateOf(passwordValue) }
    PasswordField(
        password = password,
        onPasswordChange = { password = it },
        onPasswordValidationSuccess = { }
    )
}

class PasswordPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "",
        "abcd1234",
        "1234567890"
    )
}
