package nextstep.signup.component.textField

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.PasswordError
import nextstep.signup.model.PasswordState

@Composable
fun PasswordTextField(
    password: String, onPasswordChange: (String) -> Unit, passwordState: PasswordState
) {
    BaseSignUpTextField(
        text = password,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_text_field_password),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = passwordState is PasswordState.Invalid,
        errorMessage = when (passwordState) {
            is PasswordState.Invalid -> when (passwordState.error) {
                PasswordError.Length -> stringResource(R.string.sign_up_error_password_length)
                PasswordError.Complexity -> stringResource(R.string.sign_up_error_password_requirement)
            }
            PasswordState.Valid, PasswordState.Initial -> ""
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(
        password = "Password123", onPasswordChange = {}, passwordState = PasswordState.Valid
    )
}