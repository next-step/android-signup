package nextstep.signup.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun PasswordConfirmTextFiled(
    text: String,
    validator: SignupInfoValidator,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValidation: (Boolean) -> Unit = {},
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_password_confirm),
        text = text,
        onValueChange = {
            onValueChange(it)
            onValidation(validator.checkCondition(it).isSuccess())
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        validateResult = validator.checkCondition(text)

    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFiledPreview(
    @PreviewParameter(PasswordConfirmTextFieldPreviewParameterProvider::class) params: PasswordConfirmPreviewParams,
) {
    PasswordConfirmTextFiled(
        text = params.first,
        validator = SignupInfoValidator.PasswordConfirm { params.second },
        onValueChange = { },
    )
}
private typealias PasswordConfirmPreviewParams = Pair<String, String>

private class PasswordConfirmTextFieldPreviewParameterProvider :
    PreviewParameterProvider<PasswordConfirmPreviewParams> {
    override val values: Sequence<PasswordConfirmPreviewParams>
        get() = sequenceOf(
            "password" to "password",
            "password" to "passwor1"
        )
}
