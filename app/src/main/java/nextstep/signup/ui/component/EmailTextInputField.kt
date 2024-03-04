package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R

@Composable
fun EmailTextInputField(
    email: String,
    onEmailChange: (String) -> Unit,
) {

    val errorTextRes: Int? = remember(key1 = email) {
        when {
            email.isEmpty() -> null
            !EMAIL_REGEX.toRegex().matches(email) -> {
                R.string.signup_error_email_invalid
            }

            else -> null
        }
    }

    SignUpTextInputField(
        value = email,
        label = stringResource(id = R.string.signup_email),
        onValueChange = onEmailChange,
        errorText = errorTextRes?.let { stringResource(id = it) }
    )

}

private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

@Preview(showBackground = true)
@Composable
private fun EmailTextInputFieldPreview(
    @PreviewParameter(EmailTextInputFieldPreviewParameterProvider::class) email: String
) {
    EmailTextInputField(email, {})
}

internal class EmailTextInputFieldPreviewParameterProvider :
    PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "",
        "email@google.com",
        "email@googl",
        "email.com",
    )
}
