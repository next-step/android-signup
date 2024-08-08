package nextstep.signup.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val validation = rememberSignUpTextFieldValidation(value) { EmailValidation(value) }
    val isError = !validation.isValid()
    val supportText: @Composable (() -> Unit)? =
        if (isError) {
            { Text(text = stringResource(id = R.string.error_email_format)) }
        } else {
            null
        }

    SignUpTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.sign_up_label_email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = isError,
        supportText = supportText,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview(
    @PreviewParameter(EmailTextFieldPreviewParameterProvider::class) value: String,
) {
    EmailTextField(value = value, onValueChange = {})
}

class EmailTextFieldPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "",
            "email@yopmail.com",
            "email@yopmail",
        )
}
