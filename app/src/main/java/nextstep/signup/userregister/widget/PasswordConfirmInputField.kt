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
import nextstep.signup.userregister.validation.InputValueValidator

@Composable
fun PasswordConfirmInputField(
    password: String,
    passwordConfirm: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = passwordConfirm,
        validationResult = InputValueValidator.PasswordConfirm.checkValue(password, passwordConfirm),
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
        password = "",
        passwordConfirm = "",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun PasswordConfirmInputFieldWithValuePreview() {
    PasswordConfirmInputField(
        password = "12345",
        passwordConfirm = "12345",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun PasswordConfirmInputFieldWithErrorPreview() {
    PasswordConfirmInputField(
        password = "12345",
        passwordConfirm = "1234",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}
