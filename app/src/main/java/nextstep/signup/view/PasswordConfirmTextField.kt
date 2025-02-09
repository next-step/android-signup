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
import nextstep.signup.utils.SignUpValidator

@Composable
fun PasswordConfirmTextField(
    value: String,
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isValid by remember(value, password) {
        derivedStateOf {
            SignUpValidator.isValidPasswordConfirm(
                password = password,
                passwordConfirm = value
            )
        }
    }

    SignUpBasicTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_confirm_password),
        value = value,
        errorMessage = if (isValid) "" else stringResource(R.string.signup_confirm_password_error),
        onValueChange = onValueChange,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}