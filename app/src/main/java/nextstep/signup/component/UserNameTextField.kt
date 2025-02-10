package nextstep.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun UserNameTextFiled(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_user_name),
        text = text,
        onValueChange = onValueChange,
        visualTransformation = VisualTransformation.None,
        validator = SignupInfoValidator.Username,
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview_long_name() {
    UserNameTextFiled(
        text = "김수한무거북이",
        onValueChange = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview_short_name() {
    UserNameTextFiled(
        text = "이",
        onValueChange = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview_format_error() {
    UserNameTextFiled(
        text = "2용우!",
        onValueChange = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview_normal() {
    UserNameTextFiled(
        text = "컴포즈",
        onValueChange = { },
    )
}
