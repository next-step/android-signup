package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
fun PasswordConfirmTextField(
    password: String,
    confirmPassword: String,
    onPasswordChange: (String) -> Unit,
    onValidationStateChanged: (Boolean) -> Unit
) {

    val validationResult = validatePasswordConfirm(password, confirmPassword)
    val errorMessage = when (validationResult) {
        PasswordConfirmValidationResult.Empty -> ""
        PasswordConfirmValidationResult.Mismatch -> stringResource(id = R.string.sign_up_password_mismatch_error)
        else -> ""
    }

    LaunchedEffect(key1 = confirmPassword, key2 = errorMessage) {
        onValidationStateChanged(confirmPassword.isNotBlank() && errorMessage.isBlank())
    }

    InputTextField(
        modifier = Modifier
            .testTag("passwordConfirm")
            .padding(top = 6.dp),
        value = confirmPassword,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_label_password_confirm),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = errorMessage
    )
}

private fun validatePasswordConfirm(password: String, confirm: String): PasswordConfirmValidationResult {
    return when {
        confirm.isEmpty() -> PasswordConfirmValidationResult.Empty
        (password != confirm) -> PasswordConfirmValidationResult.Mismatch
        else -> PasswordConfirmValidationResult.Valid
    }
}

sealed class PasswordConfirmValidationResult {
    data object Valid : PasswordConfirmValidationResult()
    data object Empty : PasswordConfirmValidationResult()
    data object Mismatch : PasswordConfirmValidationResult()
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview() {
    PasswordConfirmTextField(
        password = "12345678ab",
        confirmPassword = "12345678ab",
        {}, {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldErrorPreview() {
    PasswordConfirmTextField(
        password = "12345678ab",
        confirmPassword = "12345678ba",
        {}, {}
    )
}
