package nextstep.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun EmailTextFiled(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_email),
        text = text,
        onValueChange = onValueChange,
        visualTransformation = VisualTransformation.None,
        validator = SignupInfoValidator.Email,
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFiled_error() {
    EmailTextFiled(
        text = "raindragonn!gmail.com",
        onValueChange = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFiled_normal() {
    EmailTextFiled(
        text = "raindragonn@gmail.com",
        onValueChange = { },
    )
}
