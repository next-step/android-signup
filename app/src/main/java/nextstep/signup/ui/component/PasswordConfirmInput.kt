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

@Composable
fun PasswordConfirmInput(
    value: String,
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val passwordCheck = value == password

    TextField(
        value = value,
        onValueChange = onValueChange,
        isError = !passwordCheck,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (!passwordCheck) {
                Text(text = stringResource(id = R.string.passwordMismatchMessage))
            }
        },
        label = { Text(text = stringResource(id = R.string.passwordConfirmLabel)) },
        modifier = modifier
    )
}

@Preview
@Composable
private fun PasswordConfirmInputPreview() {
    var passwordConfirm by remember { mutableStateOf("") }

    SignupTheme {
        PasswordConfirmInput(
            value = passwordConfirm,
            password = "test",
            onValueChange = {
                passwordConfirm = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}