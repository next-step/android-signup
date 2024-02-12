package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
internal fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = when {
        email.isEmpty() -> null
        !EMAIL_REGEX.toRegex().matches(email) -> {
            { Text(text = stringResource(id = R.string.signup_email_format_error)) }
        }
        else -> null
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

private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

private class EmailTextFieldPreviewParameter : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("", "test@test.com", "이메일")
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview(@PreviewParameter(EmailTextFieldPreviewParameter::class) email: String) {
    MaterialTheme {
        EmailTextField(
            email = email,
            onEmailChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
