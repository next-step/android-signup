package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
internal fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = when {
        password.isEmpty() -> null
        password.length !in (8..16) -> {
            { Text(text = stringResource(id = R.string.signup_password_length_error)) }
        }
        !PASSWORD_REGEX.toRegex().matches(password) -> {
            { Text(text = stringResource(id = R.string.signup_password_format_error)) }
        }
        else -> null
    }

    TextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.signup_password_placeholder)) },
        supportingText = supportingText,
        visualTransformation = PasswordVisualTransformation(),
        isError = supportingText != null,
        singleLine = true,
    )
}

private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

private class PasswordTextFieldPreviewParameter : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("", "1q2w3e4r!", "1234567890")
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview(@PreviewParameter(PasswordTextFieldPreviewParameter::class) password: String) {
    MaterialTheme {
        PasswordTextField(
            password = password,
            onPasswordChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
