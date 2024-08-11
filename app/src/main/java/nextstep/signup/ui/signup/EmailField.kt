package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R


@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    TextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.email)) },
        placeholder = { Text(text = stringResource(id = R.string.email)) },
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailFieldPreview() {
    var email by remember { mutableStateOf("") }
    EmailField(
        email = email,
        onEmailChange = { email = it }
    )
}
