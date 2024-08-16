package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.util.SignUpValidationCheck


@Composable
fun UsernameField(
    username: String,
    onUsernameChange: (String) -> Unit,
    onUsernameValidationSuccess: (Boolean) -> Unit
) {
    val validation by remember(username) {
        derivedStateOf { SignUpValidationCheck.validateUsername(username) }
    }

    LaunchedEffect(validation) {
        onUsernameValidationSuccess(username.isNotBlank() && validation == SignUpValidation.NONE)
    }

    TextField(
        value = username,
        onValueChange = {
            onUsernameChange(it)
        },
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
private fun UsernameFieldPreview(
    @PreviewParameter(UsernamePreviewParameterProvider::class) usernameValue: String
) {
    var username by remember { mutableStateOf(usernameValue) }
    UsernameField(
        username = username,
        onUsernameChange = { username = it },
        onUsernameValidationSuccess = { }
    )
}

class UsernamePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "",
        "park",
        "kyudong3"
    )
}
