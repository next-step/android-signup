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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.domain.UsernameValidationResult

@Composable
internal fun UsernameTextField(
    username: String,
    validationResult: UsernameValidationResult,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = remember(username, validationResult) {
        if (username.isEmpty()) return@remember null
        when (validationResult) {
            UsernameValidationResult.SUCCESS -> null
            UsernameValidationResult.INVALID_LENGTH -> {
                { Text(text = stringResource(id = R.string.signup_username_length_error)) }
            }
            UsernameValidationResult.INVALID_FORMAT -> {
                { Text(text = stringResource(id = R.string.signup_username_format_error)) }
            }
        }
    }

    TextField(
        value = username,
        onValueChange = onNameChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.signup_username_placeholder)) },
        singleLine = true,
        supportingText = supportingText,
        isError = supportingText != null,
    )
}

private class UsernameTextFieldPreviewParameter :
    PreviewParameterProvider<Pair<String, UsernameValidationResult>> {
    override val values: Sequence<Pair<String, UsernameValidationResult>>
        get() = sequenceOf(
            "" to UsernameValidationResult.INVALID_LENGTH,
            "김컴포즈" to UsernameValidationResult.SUCCESS,
            "김" to UsernameValidationResult.INVALID_LENGTH,
            "12345" to UsernameValidationResult.INVALID_FORMAT,
        )
}

@Preview(showBackground = true)
@Composable
private fun UsernameTextFieldPreview(
    @PreviewParameter(UsernameTextFieldPreviewParameter::class)
    parameter: Pair<String, UsernameValidationResult>,
) {
    MaterialTheme {
        UsernameTextField(
            username = parameter.first,
            validationResult = parameter.second,
            onNameChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
