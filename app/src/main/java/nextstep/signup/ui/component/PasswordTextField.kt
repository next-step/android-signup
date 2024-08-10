package nextstep.signup.ui.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.component.PasswordValidation.PasswordValidationResult

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    validationResult: PasswordValidationResult = PasswordValidationResult.Empty,
) {
    val supportText: @Composable (() -> Unit)? =
        when (validationResult) {
            is PasswordValidationResult.FailurePasswordLength -> {
                { Text(text = stringResource(id = R.string.error_password_length)) }
            }

            is PasswordValidationResult.FailurePasswordFormat -> {
                { Text(text = stringResource(id = R.string.error_password_format)) }
            }

            else -> null
        }

    SignUpTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.sign_up_label_password)) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = validationResult.isFailure,
        supportText = supportText,
        modifier = modifier,
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview(
    @PreviewParameter(PasswordTextFieldPreviewParameterProvider::class) param: PasswordTextFieldPreviewParameter,
) {
    PasswordTextField(
        value = param.value,
        onValueChange = {},
        validationResult = param.validationResult,
    )
}

class PasswordTextFieldPreviewParameterProvider : PreviewParameterProvider<PasswordTextFieldPreviewParameter> {
    override val values: Sequence<PasswordTextFieldPreviewParameter>
        get() =
            sequenceOf(
                PasswordTextFieldPreviewParameter("", PasswordValidationResult.Empty),
                PasswordTextFieldPreviewParameter("password1234", PasswordValidationResult.Success),
                PasswordTextFieldPreviewParameter(
                    "password",
                    PasswordValidationResult.FailurePasswordFormat,
                ),
                PasswordTextFieldPreviewParameter(
                    "1234",
                    PasswordValidationResult.FailurePasswordLength,
                ),
            )
}

data class PasswordTextFieldPreviewParameter(
    val value: String,
    val validationResult: PasswordValidationResult,
)
