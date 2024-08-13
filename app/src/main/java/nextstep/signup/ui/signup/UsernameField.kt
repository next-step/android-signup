package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.util.SignUpValidationCheck


@Composable
fun UsernameField(
    username: String,
    onUsernameChange: (String) -> Unit
) {
    val validation by remember(username) {
        derivedStateOf { SignUpValidationCheck.validateUsername(username) }
    }

    TextField(
        value = username,
        onValueChange = { onUsernameChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.username)) },
        supportingText = {
            when (validation) {
                SignUpValidation.INVALID_LENGTH ->
                    Text(text = stringResource(id = R.string.error_invalid_username_length))
                SignUpValidation.INVALID_CHARACTER ->
                    Text(text = stringResource(id = R.string.error_invalid_username))
                else -> {}
            }
        },
        isError = username.isNotBlank() && validation != SignUpValidation.NONE,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun UsernameFieldPreview() {
    var emptyUsername by remember { mutableStateOf("") }
    var validUsername by remember { mutableStateOf("park") }
    var invalidUsername by remember { mutableStateOf("kyudong3") }
    Column {
        UsernameField(
            username = emptyUsername,
            onUsernameChange = { emptyUsername = it }
        )
        UsernameField(
            username = invalidUsername,
            onUsernameChange = { invalidUsername = it }
        )
        UsernameField(
            username = validUsername,
            onUsernameChange = { validUsername = it }
        )
    }
}

