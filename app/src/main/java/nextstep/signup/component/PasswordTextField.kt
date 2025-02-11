package nextstep.signup.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun PasswordTextFiled(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValidation: (Boolean) -> Unit = {},
) {

    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_password),
        text = text,
        onValueChange = {
            onValueChange(it)
            onValidation(SignupInfoValidator.Password.checkCondition(it).isSuccess())
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        validateResult = SignupInfoValidator.Password.checkCondition(text)
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFiledPreview_format_error() {
    PasswordTextFiled(
        text = "123123123",
        onValueChange = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFiledPreview_length_error() {
    PasswordTextFiled(
        text = "1q2w3e",
        onValueChange = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFiledPreview_normal() {
    PasswordTextFiled(
        text = "1q2w3e4r",
        onValueChange = { },
    )
}
