package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R

@Composable
fun PasswordConfirmTextField(
    modifier: Modifier = Modifier,
    password: String,
    passwordConfirm: String,
    onValueChange: (String) -> Unit
) {
    val supportingText: @Composable (() -> Unit)? =
        when (ConfirmError.checkBy(src = password, dst = passwordConfirm)) {
            ConfirmError.NOT_MATCH -> {
                { Text(text = stringResource(R.string.password_not_confirm_error)) }
            }

            ConfirmError.EMPTY,
            ConfirmError.NONE -> {
                null
            }

        }
    TextField(
        modifier = modifier.fillMaxWidth(),
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

@Preview
@Composable
private fun Preview() {
    PasswordConfirmTextField(
        modifier = Modifier,
        password = "",
        passwordConfirm = "",
        onValueChange = {}
    )
}

enum class ConfirmError {
    EMPTY,
    NOT_MATCH,
    NONE;

    companion object {
        fun checkBy(src: String, dst: String): ConfirmError {
            return when {
                dst.isEmpty() -> {
                    EMPTY
                }

                src != dst -> {
                    NOT_MATCH
                }

                else -> {
                    NONE
                }
            }
        }
    }
}
