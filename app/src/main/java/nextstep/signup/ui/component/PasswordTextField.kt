package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.domain.PasswordValidationResult

@Composable
internal fun PasswordTextField(
    password: String,
    validationResult: PasswordValidationResult,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = remember(password, validationResult) {
        if (password.isEmpty()) return@remember null
        when (validationResult) {
            PasswordValidationResult.SUCCESS -> null
            PasswordValidationResult.INVALID_LENGTH -> {
                { Text(text = stringResource(id = R.string.signup_password_length_error)) }
            }
            PasswordValidationResult.INVALID_FORMAT -> {
                { Text(text = stringResource(id = R.string.signup_password_format_error)) }
            }
        }
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

private class PasswordTextFieldPreviewParameter :
    PreviewParameterProvider<Pair<String, PasswordValidationResult>> {
    override val values: Sequence<Pair<String, PasswordValidationResult>>
        get() = sequenceOf(
            "" to PasswordValidationResult.INVALID_LENGTH,
            "1q2w3e4r!" to PasswordValidationResult.SUCCESS,
            "1234567890" to PasswordValidationResult.INVALID_FORMAT,
        )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview(
    @PreviewParameter(PasswordTextFieldPreviewParameter::class)
    parameter: Pair<String, PasswordValidationResult>,
) {
    MaterialTheme {
        PasswordTextField(
            password = parameter.first,
            validationResult = parameter.second,
            onPasswordChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
