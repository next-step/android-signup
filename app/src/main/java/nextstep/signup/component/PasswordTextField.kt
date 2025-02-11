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
internal fun PasswordTextFiled(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValidation: (Boolean) -> Unit = {},
    validator: SignupInfoValidator = SignupInfoValidator.Password,
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_password),
        text = text,
        onValueChange = {
            onValueChange(it)
            onValidation(validator.checkCondition(it).isSuccess())
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        validateResult = validator.checkCondition(text)
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFiledPreview(
    @PreviewParameter(PasswordTextFieldPreviewParameterProvider::class) password: String,
) {
    PasswordTextFiled(
        text = password,
        onValueChange = { },
    )
}

private class PasswordTextFieldPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "123123123",
            "1q2w3e",
            "1q2w3e4r",
        )
}
