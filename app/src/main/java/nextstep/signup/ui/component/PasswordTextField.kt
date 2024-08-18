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

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    InputTextField(
        modifier = Modifier.padding(top = 6.dp),
        value = password,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_label_password),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = getPasswordErrorMessage(password)
    )
}

@Composable
fun getPasswordErrorMessage(password: String): String {
    return when {
        password.isEmpty() -> ""
        password.length !in 8..16 -> {
            stringResource(id = R.string.sign_up_password_size_error)
        }

        !password.matches(Regex(PASSWORD_REGEX)) -> {
            stringResource(id = R.string.sign_up_password_format_error)
        }

        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(password = "12345678ab") {}
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldErrorPreview() {
    PasswordTextField(password = "12345678") {}
}

