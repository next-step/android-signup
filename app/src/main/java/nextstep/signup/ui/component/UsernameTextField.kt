package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R

@Composable
fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val usernameValidation =
        rememberSignUpTextFieldValidation(value) { UsernameValidation(value) }
    val isError = !usernameValidation.isValid()
    val supportText: @Composable (() -> Unit)? =
        when {
            isError -> {
                when (usernameValidation.errorType) {
                    UsernameValidation.ErrorType.LENGTH -> {
                        { Text(text = stringResource(id = R.string.error_username_length)) }
                    }

                    UsernameValidation.ErrorType.FORMAT -> {
                        { Text(text = stringResource(id = R.string.error_username_format)) }
                    }

                    else -> null
                }
            }

            else -> null
        }

    SignUpTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.sign_up_label_username)) },
        modifier = modifier,
        isError = isError,
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
