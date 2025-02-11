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
internal fun UserNameTextFiled(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValidation: (Boolean) -> Unit = {},
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_user_name),
        text = text,
        onValueChange = {
            onValueChange(it)
            onValidation(SignupInfoValidator.Username.checkCondition(it).isSuccess())
        },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        validateResult = SignupInfoValidator.Username.checkCondition(text)
    )
}


@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview(
    @PreviewParameter(UsernamePreviewParameterProvider::class) username: String,
) {
    UserNameTextFiled(
        text = username,
        onValueChange = {}
    )
}

class UsernamePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "김수한무거북이",
            "이",
            "2용우!",
            "컴포즈",
        )
}
