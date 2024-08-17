package nextstep.signup.component.textField

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.PasswordConfirmState

@Composable
fun PasswordConfirmTextField(
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit,
    passwordConfirmState: PasswordConfirmState,
) {
    BaseSignUpTextField(
        text = passwordConfirm,
        onValueChange = onPasswordConfirmChange,
        label = stringResource(R.string.sign_up_text_field_password_confirm),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = when (passwordConfirmState) {
            PasswordConfirmState.Mismatch -> stringResource(R.string.sign_up_error_password_mismatch)
            PasswordConfirmState.Valid, PasswordConfirmState.Initial -> null
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PasswordConfirmTextFieldPreview() {
    PasswordConfirmTextField(
        passwordConfirm = "Password123",
        onPasswordConfirmChange = {},
        passwordConfirmState = PasswordConfirmState.Valid
    )
}