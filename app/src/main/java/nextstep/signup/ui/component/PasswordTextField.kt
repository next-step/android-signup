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

private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

@Preview
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String = "",
    onValueChange: (String) -> Unit = {}
) {
    val supportingText: @Composable (() -> Unit)? = when {
        password.isEmpty() -> {
            null
        }

        password.length !in 8..16 -> {
            {
                Text(text = stringResource(R.string.password_length_error))
            }
        }

        PASSWORD_REGEX.toRegex()
            .matches(password)
            .not() -> {
            {
                Text(text = stringResource(R.string.your_password_must_contain_english_and_numbers))
            }
        }

        else -> {
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
    val supportingText: @Composable (() -> Unit)? = when {
        passwordConfirm.isEmpty() -> {
            null
        }
        password != passwordConfirm -> {
            {
                Text(text = stringResource(R.string.password_not_confirm_error))
            }
        }
        else -> {
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
