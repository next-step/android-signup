package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R

@Preview
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String = "",
    onValueChange: (String) -> Unit = {}
) {
    val supportingText: @Composable (() -> Unit)? =
        when (PasswordError.checkBy(password = password)) {
            PasswordError.OUT_OF_RANGE_LENGTH -> {
                { Text(text = stringResource(R.string.password_length_error)) }
            }

            PasswordError.CANNOT_CONTAIN_ENGLISH_AND_NUMBERS -> {
                { Text(text = stringResource(R.string.your_password_must_contain_english_and_numbers)) }
            }

            PasswordError.NONE -> {
                null
            }
        }
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = password,
        onValueChange = { value ->
            onValueChange(value)
        },
        label = {
            Text(stringResource(R.string.password))
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        supportingText = supportingText,
        isError = supportingText != null
    )
}

@Preview
@Composable
fun PasswordConfirmTextField(
    modifier: Modifier = Modifier,
    password: String = "",
    passwordConfirm: String = "",
    onValueChange: (String) -> Unit = {}
) {
    val supportingText: @Composable (() -> Unit)? =
        when (PasswordError.checkBy(password = password, confirm = passwordConfirm)) {
            PasswordError.NOT_MATCH -> {
                { Text(text = stringResource(R.string.password_not_confirm_error)) }
            }

            PasswordError.OUT_OF_RANGE_LENGTH,
            PasswordError.CANNOT_CONTAIN_ENGLISH_AND_NUMBERS,
            PasswordError.NONE -> {
                null
            }
        }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = passwordConfirm,
        onValueChange = { value ->
            onValueChange(value)
        },
        label = {
            Text(stringResource(R.string.password_confirm))
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        supportingText = supportingText,
        isError = supportingText != null
    )
}

enum class PasswordError {
    OUT_OF_RANGE_LENGTH,
    CANNOT_CONTAIN_ENGLISH_AND_NUMBERS,
    NONE;

    companion object {
        private val VALID_RANGE = 8..16
        private val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()

        fun checkBy(password: String): PasswordError {
            return when {
                password.isEmpty() -> {
                    NONE
                }

                password.length !in VALID_RANGE -> {
                    OUT_OF_RANGE_LENGTH
                }

                PASSWORD_REGEX.matches(input = password)
                    .not() -> {
                    CANNOT_CONTAIN_ENGLISH_AND_NUMBERS
                }

                else -> {
                    NONE
                }
            }
        }
    }
}
