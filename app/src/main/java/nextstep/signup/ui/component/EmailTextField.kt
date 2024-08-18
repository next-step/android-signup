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
import androidx.compose.ui.unit.dp
import nextstep.signup.R
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

private fun validateEmail(email: String): ValidationResult {
    return when {
        email.isEmpty() -> ValidationResult.Empty
        !email.matches(Regex(EMAIL_REGEX)) -> ValidationResult.InvalidFormat
        else -> ValidationResult.Valid
    }
}

sealed class ValidationResult {
    data object Valid : ValidationResult()
    data object Empty : ValidationResult()
    data object InvalidFormat : ValidationResult()
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
    EmailTextField(email = "thxallgrace@gmail.com", {}, {})
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldErrorPreview() {
    EmailTextField(email = "thxallgrace@gmail", {}, {})
}


