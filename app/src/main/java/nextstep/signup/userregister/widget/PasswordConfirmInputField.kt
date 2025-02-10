package nextstep.signup.userregister.widget

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.component.UserRegisterTextField

@Composable
fun PasswordConfirmInputField(
    value: String,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = value,
        errorMessage = errorMessage,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_password_confirm_label),
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun PasswordConfirmInputFieldPreview() {
    PasswordConfirmInputField(
        value = "",
        errorMessage = "",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun PasswordConfirmInputFieldWithValuePreview() {
    PasswordConfirmInputField(
        value = "123456789",
        errorMessage = "",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun PasswordConfirmInputFieldWithErrorPreview() {
    PasswordConfirmInputField(
        value = "123456789",
        errorMessage = "에러",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}
