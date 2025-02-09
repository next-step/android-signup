package nextstep.signup.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isInvalidFormatError by remember(value) {
        derivedStateOf {
            value.isNotEmpty() && !value.matches(Regex(RegexConst.EMAIL_REGEX))
        }
    }

    SignUpTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.sign_up_email),
        isError = isInvalidFormatError,
        supportingText = if (isInvalidFormatError) stringResource(R.string.sign_up_email_invalid_format_error) else ""
    )
}

private class EmailTextFieldPreviewParameter: PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("yangsooplus0@gmail.com", "yangsooplus@naver", "abcde", "")

}

@Preview
@Composable
private fun EmailTextFieldPreview(
    @PreviewParameter(EmailTextFieldPreviewParameter::class) value: String
) {
    SignupTheme {
        EmailTextField(
            value = value,
            onValueChange = {}
        )
    }
}