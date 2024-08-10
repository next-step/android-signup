package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

@Composable
fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val errorMessageResId = getPasswordErrorMessage(value)

    TextField(
        value = value,
        onValueChange = onValueChange,
        isError = errorMessageResId != null,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (errorMessageResId != null) {
                Text(text = stringResource(id = errorMessageResId))
            }
        },
        label = { Text(text = stringResource(id = R.string.passwordLabel)) },
        modifier = modifier
    )
}

fun getPasswordErrorMessage(value: String): Int? {
    return when {
        value.isEmpty() -> null
        !value.matches(Regex(PASSWORD_REGEX)) -> R.string.passwordLValidationMessage
        else -> null
    }
}

@Preview
@Composable
private fun PasswordInputPreview() {
    var password by remember { mutableStateOf("") }

    SignupTheme {
        PasswordInput(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}