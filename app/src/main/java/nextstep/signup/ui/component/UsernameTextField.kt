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
internal fun UsernameTextField(
    username: String,
    onNameChange: (String) -> Unit,
) {
    TextField(
        value = username,
        onValueChange = onNameChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_username)) },
        singleLine = true,
    )
}

@Preview
@Composable
private fun UsernameTextFieldPreview() {
    MaterialTheme {
        UsernameTextField(
            username = "김컴포즈",
            onNameChange = {}
        )
    }
}

@Preview
@Composable
private fun UsernameTextFieldEmptyPreview() {
    MaterialTheme {
        UsernameTextField(
            username = "",
            onNameChange = {}
        )
    }
}
