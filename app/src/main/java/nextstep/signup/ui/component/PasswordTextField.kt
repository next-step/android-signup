package nextstep.signup.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val validation = rememberSignUpTextFieldValidation(value) { PasswordValidation(value) }
    val isError = !validation.isValid()
    val supportText: @Composable (() -> Unit)? =
        when {
            isError -> {
                when (validation.errorType) {
                    PasswordValidation.ErrorType.LENGTH -> {
                        { Text(text = stringResource(id = R.string.error_password_length)) }
                    }

                    PasswordValidation.ErrorType.FORMAT -> {
                        { Text(text = stringResource(id = R.string.error_password_format)) }
                    }

                    else -> null
                }
            }

            else -> null
        }

    SignUpTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.sign_up_label_password)) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = isError,
        supportText = supportText,
        modifier = modifier,
    )
}
