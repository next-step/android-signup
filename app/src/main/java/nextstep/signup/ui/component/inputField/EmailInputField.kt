package nextstep.signup.ui.component.inputField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.util.SignUpInputValidation

@Composable
fun EmailInputField(
    email: String,
    onValueChange: (String) -> Unit,
    onValidationSuccess: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(R.string.sign_up_email),
) {
    val context = LocalContext.current
    val supportingText by remember(email) {
        derivedStateOf {
            when {
                email.isBlank() -> ""
                !email.matches(SignUpInputValidation.EMAIL_REGEX.toRegex()) -> context.getString(R.string.sign_up_email_input_validation_message)
                else -> ""
            }
        }
    }

    LaunchedEffect(key1 = email, key2 = supportingText) {
        onValidationSuccess(email.isNotBlank() && supportingText.isBlank())
    }

    TextField(
        modifier = modifier
            .testTag("email")
            .fillMaxWidth(),
        value = email,
        onValueChange = onValueChange,
        label = { Text(hint) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        supportingText = { Text(supportingText) },
        isError = supportingText.isNotBlank(),
    )
}

@Preview
@Composable
private fun EmailInputFieldPreview() {
    EmailInputField(
        email = "",
        onValueChange = {},
        onValidationSuccess = {},
    )
}

@Preview
@Composable
private fun EmailInputFieldPreviewWithLabel() {
    EmailInputField(
        email = "",
        onValueChange = {},
        onValidationSuccess = {},
        hint = "Email",
    )
}

@Preview
@Composable
private fun EmailInputFieldPreviewWithValue() {
    EmailInputField(
        email = "abc@nextstep.com",
        onValueChange = {},
        onValidationSuccess = {},
    )
}

@Preview
@Composable
private fun EmailInputFieldPreviewWithValueAndLabel() {
    EmailInputField(
        email = "abc@nextstep.com",
        onValueChange = {},
        onValidationSuccess = {},
        hint = "Email",
    )
}

@Preview
@Composable
private fun EmailInputFieldPreviewErrorEmailFormat() {
    EmailInputField(
        email = "abcnextstep.com",
        onValueChange = {},
        onValidationSuccess = {},
        hint = "Email",
    )
}
