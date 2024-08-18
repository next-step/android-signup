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
import nextstep.signup.util.ValidationPatterns.PASSWORD_REGEX

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    onValidationStateChanged: (Boolean) -> Unit
) {
    val validationResult = validatePassword(password)
    val errorMessage = when (validationResult) {
        PasswordValidationResult.Empty -> ""
        PasswordValidationResult.InvalidSize -> stringResource(id = R.string.sign_up_password_size_error)
        PasswordValidationResult.InvalidFormat -> stringResource(id = R.string.sign_up_password_format_error)
        else -> ""
    }

    LaunchedEffect(key1 = password, key2 = errorMessage) {
        onValidationStateChanged(password.isNotBlank() && errorMessage.isBlank())
    }

    InputTextField(
        modifier = Modifier.padding(top = 6.dp),
        value = password,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_label_password),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = errorMessage
    )
}

private fun validatePassword(password: String): PasswordValidationResult {
    return when {
        password.isEmpty() -> PasswordValidationResult.Empty
        password.length !in 8..16 ->  PasswordValidationResult.InvalidSize
        !password.matches(Regex(PASSWORD_REGEX))  -> PasswordValidationResult.InvalidFormat
        else -> PasswordValidationResult.Valid
    }
}

sealed class PasswordValidationResult {
    data object Valid : PasswordValidationResult()
    data object Empty : PasswordValidationResult()
    data object InvalidSize : PasswordValidationResult()
    data object InvalidFormat : PasswordValidationResult()
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    PasswordTextField(password = "12345678ab", {}, {})
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldErrorPreview() {
    PasswordTextField(password = "12345678", {}, {})
}

