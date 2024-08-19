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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.component.PasswordValidationResult.Companion.validatePassword
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
        modifier = Modifier
            .testTag("password")
            .padding(top = 6.dp),
        value = password,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_label_password),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = errorMessage
    )
}

enum class PasswordValidationResult {
    Valid,
    Empty,
    InvalidSize,
    InvalidFormat;

    companion object {
        fun validatePassword(password: String): PasswordValidationResult {
            return when {
                password.isEmpty() -> Empty
                password.length !in 8..16 ->  InvalidSize
                !password.matches(Regex(PASSWORD_REGEX))  -> InvalidFormat
                else -> Valid
            }
        }
    }
}

class PasswordPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "123",
        "12345678ab",
        "12345678"
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview(
    @PreviewParameter(PasswordPreviewParameterProvider::class) password: String
) {
    PasswordTextField(password = password, {}, {})
}
