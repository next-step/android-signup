package nextstep.signup.ui.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.component.UsernameValidation.UsernameValidationResult

@Composable
fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    validationResult: UsernameValidationResult = UsernameValidationResult.Empty,
) {
    val supportText: @Composable (() -> Unit)? =
        when (validationResult) {
            is UsernameValidationResult.FailureUsernameLength -> {
                { Text(text = stringResource(id = R.string.error_username_length)) }
            }

            is UsernameValidationResult.FailureUsernameFormat -> {
                { Text(text = stringResource(id = R.string.error_username_format)) }
            }

            else -> null
        }

    SignUpTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.sign_up_label_username)) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier,
        isError = validationResult.isFailure,
        supportText = supportText,
    )
}

@Preview(showBackground = true)
@Composable
fun UsernameTextFieldPreview(
    @PreviewParameter(UsernameTextFieldPreviewParameterProvider::class) value: String,
) {
    UsernameTextField(value = value, onValueChange = {})
}

class UsernameTextFieldPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() =
            sequenceOf(
                "",
                "user",
                "u",
                "username",
            )
}
