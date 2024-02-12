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
import nextstep.signup.domain.PasswordConfirmValidationResult

@Composable
internal fun PasswordConfirmTextField(
    password: String,
    validationResult: PasswordConfirmValidationResult,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = remember(password, validationResult) {
        if (password.isEmpty()) return@remember null
        when (validationResult) {
            PasswordConfirmValidationResult.SUCCESS -> null
            PasswordConfirmValidationResult.INVALID -> {
                { Text(text = stringResource(id = R.string.signup_password_confirm_diff_error)) }
            }
        }
    }

    TextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.signup_password_confirm_placeholder)) },
        supportingText = supportingText,
        isError = supportingText != null,
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

private class PasswordConfirmTextFieldPreviewParameter :
    PreviewParameterProvider<Pair<String, PasswordConfirmValidationResult>> {
    override val values: Sequence<Pair<String, PasswordConfirmValidationResult>>
        get() = sequenceOf(
            "" to PasswordConfirmValidationResult.INVALID,
            "1q2w3e4r!" to PasswordConfirmValidationResult.SUCCESS,
            "1234" to PasswordConfirmValidationResult.INVALID,
        )
}


@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview(
    @PreviewParameter(PasswordConfirmTextFieldPreviewParameter::class)
    parameter: Pair<String, PasswordConfirmValidationResult>,
) {
    MaterialTheme {
        PasswordConfirmTextField(
            password = parameter.first,
            validationResult = parameter.second,
            onPasswordChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
