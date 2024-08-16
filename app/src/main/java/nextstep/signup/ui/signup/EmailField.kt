package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.util.SignUpValidationCheck


@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit,
    onEmailValidationSuccess: (Boolean) -> Unit
) {
    val validation by remember(email) {
        derivedStateOf { SignUpValidationCheck.validateEmail(email) }
    }
    LaunchedEffect(validation) {
        onEmailValidationSuccess(email.isNotBlank() && validation == SignUpValidation.NONE)
    }

    TextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.email)) },
        supportingText = {
            when (validation) {
                SignUpValidation.INVALID_EMAIL ->
                    Text(text = stringResource(id = R.string.error_invalid_email))
                else -> {}
            }
        },
        isError = email.isNotBlank() && validation != SignUpValidation.NONE,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailFieldPreview(
    @PreviewParameter(EmailPreviewParameterProvider::class) emailValue: String
) {
    var email by remember { mutableStateOf(emailValue) }
    EmailField(
        email = email,
        onEmailChange = { email = it },
        onEmailValidationSuccess = { }
    )
}

class EmailPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "",
        "kyudong3@gmail.com",
        "kyudong3@gmail"
    )
}
