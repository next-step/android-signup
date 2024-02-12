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
import nextstep.signup.domain.EmailValidationResult

@Composable
internal fun EmailTextField(
    email: String,
    validationResult: EmailValidationResult,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = remember(email, validationResult) {
        if (email.isEmpty()) return@remember null
        when (validationResult) {
            EmailValidationResult.SUCCESS -> null
            EmailValidationResult.INVALID_FORMAT -> {
                { Text(text = stringResource(id = R.string.signup_email_format_error)) }
            }
        }
    }

    TextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.signup_email_placeholder)) },
        supportingText = supportingText,
        isError = supportingText != null,
        singleLine = true,
    )
}

private class EmailTextFieldPreviewParameter :
    PreviewParameterProvider<Pair<String, EmailValidationResult>> {
    override val values: Sequence<Pair<String, EmailValidationResult>>
        get() = sequenceOf(
            "" to EmailValidationResult.INVALID_FORMAT,
            "test@test.com" to EmailValidationResult.SUCCESS,
            "이메일" to EmailValidationResult.INVALID_FORMAT,
        )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview(
    @PreviewParameter(EmailTextFieldPreviewParameter::class)
    parameter: Pair<String, EmailValidationResult>,
) {
    MaterialTheme {
        EmailTextField(
            email = parameter.first,
            validationResult = parameter.second,
            onEmailChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
