package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.util.ValidationPatterns.PASSWORD_REGEX
import nextstep.signup.util.ValidationPatterns.USERNAME_REGEX

@Composable
fun PasswordConfirmTextField(
    password: String,
    confirmPassword: String,
    onPasswordChange: (String) -> Unit
) {
    InputTextField(
        modifier = Modifier.padding(top = 6.dp),
        value = confirmPassword,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_label_password_confirm),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = getConfirmPasswordErrorMessage(password, confirmPassword)
    )
}

@Composable
fun getConfirmPasswordErrorMessage(password: String, confirm: String): String {
    return if (password != confirm) {
        stringResource(id = R.string.sign_up_password_mismatch_error)
    } else ""
}

@Preview(showBackground = true)
@Composable
fun PasswordConfirmTextFieldPreview() {
    PasswordConfirmTextField(
        password = "12345678ab",
        confirmPassword = "12345678ab"
    ) {}
}

@Preview(showBackground = true)
@Composable
fun PasswordConfirmTextFieldErrorPreview() {
    PasswordConfirmTextField(
        password = "12345678ab",
        confirmPassword = "12345678ba"
    ) {}
}
