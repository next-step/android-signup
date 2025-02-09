package nextstep.signup.view

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.R
import nextstep.signup.utils.PasswordErrorType
import nextstep.signup.utils.SignUpValidator

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val errorType by remember(value) {
        derivedStateOf { SignUpValidator.validatePassword(value) }
    }

    SignUpBasicTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_password),
        value = value,
        errorMessage = when(errorType) {
            PasswordErrorType.NO_ERROR -> ""
            PasswordErrorType.PASSWORD_LENGTH_ERROR -> stringResource(R.string.signup_password_length_error)
            PasswordErrorType.PASSWORD_FORMAT_ERROR -> stringResource(R.string.signup_password_format_error)
        },
        onValueChange = onValueChange,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

    )
}