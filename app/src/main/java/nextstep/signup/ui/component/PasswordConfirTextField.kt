package nextstep.signup.ui.component

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
import nextstep.signup.R.string
import nextstep.signup.ui.component.PasswordConfirmValidation.PasswordConfirmValidationResult

@Composable
fun PasswordConfirmTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    validationResult: PasswordConfirmValidationResult = PasswordConfirmValidationResult.Empty,
) {
    val supportText: @Composable (() -> Unit)? =
        if (validationResult is PasswordConfirmValidationResult.FailurePasswordNotMatch) {
            { Text(text = stringResource(id = R.string.error_password_confirm)) }
        } else {
            null
        }

    SignUpTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = string.sign_up_label_password_confirm)) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = validationResult.isFailure,
        supportText = supportText,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordConfirmTextFieldPreview(
    @PreviewParameter(PasswordConfirmTextFieldPreviewParameterProvider::class) param: PasswordConfirmTextFieldPreviewParameter,
) {
    PasswordConfirmTextField(
        value = param.value,
        onValueChange = {},
        validationResult = param.validationResult,
    )
}

class PasswordConfirmTextFieldPreviewParameterProvider : PreviewParameterProvider<PasswordConfirmTextFieldPreviewParameter> {
    override val values: Sequence<PasswordConfirmTextFieldPreviewParameter>
        get() =
            sequenceOf(
                PasswordConfirmTextFieldPreviewParameter("password1234", PasswordConfirmValidationResult.Success),
                PasswordConfirmTextFieldPreviewParameter(
                    "password1234",
                    PasswordConfirmValidationResult.FailurePasswordNotMatch,
                ),
                PasswordConfirmTextFieldPreviewParameter("", PasswordConfirmValidationResult.Empty),
            )
}

data class PasswordConfirmTextFieldPreviewParameter(
    val value: String,
    val validationResult: PasswordConfirmValidationResult,
)
