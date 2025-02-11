package nextstep.signup.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun EmailTextFiled(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValidation: (Boolean) -> Unit = {},
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_email),
        text = text,
        onValueChange = {
            onValueChange(it)
            onValidation(SignupInfoValidator.Email.checkCondition(it).isSuccess())
        },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        validateResult = SignupInfoValidator.Email.checkCondition(text)
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFiledPreview(
    @PreviewParameter(EmailTextFieldPreviewParameterProvider::class) email: String,
) {
    EmailTextFiled(
        text = email,
        onValueChange = { },
    )
}

class EmailTextFieldPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "raindragonn!gmail.com",
            "raindragonn@gmail.com",
        )
}
