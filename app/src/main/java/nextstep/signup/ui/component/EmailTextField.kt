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
internal fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.signup_placeholder_email)) },
        singleLine = true,
    )
}

@Preview
@Composable
private fun EmailTextFieldPreview() {
    MaterialTheme {
        EmailTextField(
            email = "test@test.com",
            onEmailChange = {}
        )
    }
}

@Preview
@Composable
private fun EmailTextFieldEmptyPreview() {
    MaterialTheme {
        EmailTextField(
            email = "",
            onEmailChange = {}
        )
    }
}
