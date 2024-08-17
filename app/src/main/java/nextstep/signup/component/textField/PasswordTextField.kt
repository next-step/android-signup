package nextstep.signup.component.textField

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.PasswordError
import nextstep.signup.model.PasswordState
import nextstep.signup.valid.RegexBasedSignUpValidator
import nextstep.signup.valid.SignUpValidator

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    onValidationResult: (PasswordState) -> Unit,
    validator: SignUpValidator = RegexBasedSignUpValidator()
) {
    val passwordState by remember(password) {
        derivedStateOf { validator.validatePassword(password) }
    }
    LaunchedEffect(passwordState) {
        onValidationResult(passwordState)
    }

    BaseSignUpTextField(
        text = password,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_text_field_password),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = when (passwordState) {
            is PasswordState.Invalid -> when ((passwordState as PasswordState.Invalid).error) {
                PasswordError.Length -> stringResource(R.string.sign_up_error_password_length)
                PasswordError.Complexity -> stringResource(R.string.sign_up_error_password_requirement)
            }
            PasswordState.Valid, PasswordState.Initial -> null
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(
        password = "Password123", onPasswordChange = {}, onValidationResult = {}
    )
}