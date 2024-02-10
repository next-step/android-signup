package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R

@Composable
internal fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_password)) },
        singleLine = true,
    )
}


@Preview
@Composable
private fun PasswordTextFieldPreview() {
    MaterialTheme {
        PasswordTextField(
            password = "12345678",
            onPasswordChange = {}
        )
    }
}

@Preview
@Composable
private fun EmailTextFieldEmptyPreview() {
    MaterialTheme {
        PasswordTextField(
            password = "",
            onPasswordChange = {}
        )
    }
}
