package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.core.validation.EmailValidationResult
import nextstep.signup.ui.ThemePreviews

@Composable
internal fun EmailTextField(
    email: String,
    emailValidationResult: EmailValidationResult,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val supportingText: @Composable (() -> Unit)? = remember(email, emailValidationResult) {
        if (email.isEmpty()) return@remember null
        when (emailValidationResult) {
            EmailValidationResult.VALID -> null
            EmailValidationResult.INVALID_FORMAT -> {
                { Text(text = stringResource(id = R.string.signup_invalid_email)) }
            }
        }
    }

    SignUpTextField(
        modifier = modifier.testTag("email"),
        value = email,
        onValueChange = onEmailChange,
        label = { Text(text = stringResource(id = R.string.signup_email)) },
        supportingText = supportingText,
    )
}

private class EmailTextFieldPreviewParameterProvider : PreviewParameterProvider<Pair<String, EmailValidationResult>> {
    override val values = sequenceOf(
        "" to EmailValidationResult.VALID,
        "invalidemail" to EmailValidationResult.INVALID_FORMAT,
        "test@example.com" to EmailValidationResult.VALID
    )
}

@ThemePreviews
@Composable
private fun PreviewEmailTextField(
    @PreviewParameter(EmailTextFieldPreviewParameterProvider::class) emailData: Pair<String, EmailValidationResult>
) {
    EmailTextField(
        email = emailData.first,
        emailValidationResult = emailData.second,
        onEmailChange = {},
        modifier = Modifier
    )
}
