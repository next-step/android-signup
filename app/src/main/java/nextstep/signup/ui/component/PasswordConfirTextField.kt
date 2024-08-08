package nextstep.signup.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.R
import nextstep.signup.R.string

@Composable
fun PasswordConfirmTextField(
    value: String,
    target: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val validation =
        rememberSignUpTextFieldValidation(value, target) {
            PasswordConfirmValidation(
                value,
                target,
            )
        }

    val isError = !validation.isValid()
    val supportText: @Composable (() -> Unit)? =
        if (isError) {
            { Text(text = stringResource(id = R.string.error_password_confirm)) }
        } else {
            null
        }

    SignUpTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = string.sign_up_label_password_confirm)) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = isError,
        supportText = supportText,
        modifier = modifier,
    )
}
