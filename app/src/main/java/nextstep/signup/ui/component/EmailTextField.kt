package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.component.ValidationResult.Companion.validateEmail
import nextstep.signup.util.ValidationPatterns.EMAIL_REGEX

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    onValidationStateChanged: (Boolean) -> Unit
) {

    val validationResult = validateEmail(email)
    val errorMessage = when (validationResult) {
        ValidationResult.Empty -> ""
        ValidationResult.InvalidFormat -> stringResource(id = R.string.sign_up_email_error)
        else -> ""
    }

    LaunchedEffect(key1 = email, key2 = errorMessage) {
        onValidationStateChanged(email.isNotBlank() && errorMessage.isBlank())
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

enum class ValidationResult {
    Valid,
    Empty,
    InvalidFormat;

    companion object {
        fun validateEmail(email: String): ValidationResult {
            return when {
                email.isEmpty() -> Empty
                !email.matches(Regex(EMAIL_REGEX)) -> InvalidFormat
                else -> Valid
            }
        }
    }
}

class EmailPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "thxallgrace@gmail.com",
        "thxallgrace@gmail",
        "thxallgrace"
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview(
    @PreviewParameter(EmailPreviewParameterProvider::class) email: String
) {
    EmailTextField(email = email, {}, {})
}


