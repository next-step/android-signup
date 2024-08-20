package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.util.ValidationPatterns.EMAIL_REGEX

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    validationResult: EmailValidationResult
) {
    val errorMessage = when (validationResult) {
        EmailValidationResult.Empty -> ""
        EmailValidationResult.InvalidFormat -> stringResource(id = R.string.sign_up_email_error)
        else -> ""
    }

    InputTextField(
        modifier = Modifier
            .testTag("email")
            .padding(top = 6.dp),
        value = email,
        onValueChange = onEmailChange,
        label = stringResource(R.string.sign_up_label_email),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        errorMessage = errorMessage
    )
}

enum class EmailValidationResult {
    Valid,
    Empty,
    InvalidFormat;

    companion object {
        fun validateEmail(email: String): EmailValidationResult {
            return when {
                email.isEmpty() -> Empty
                !email.matches(Regex(EMAIL_REGEX)) -> InvalidFormat
                else -> Valid
            }
        }
    }
}

class EmailPreviewParameterProvider :
    PreviewParameterProvider<Pair<String, EmailValidationResult>> {
    override val values: Sequence<Pair<String, EmailValidationResult>> = sequenceOf(
        "thxallgrace@gmail.com" to EmailValidationResult.Valid,
        "thxallgrace@gmail" to EmailValidationResult.InvalidFormat,
        "thxallgrace" to EmailValidationResult.InvalidFormat
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview(
    @PreviewParameter(EmailPreviewParameterProvider::class)
    parameter: Pair<String, EmailValidationResult>,
) {
    EmailTextField(
        email = parameter.first,
        onEmailChange = {},
        validationResult = parameter.second
    )
}


