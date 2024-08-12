package nextstep.signup.ui.component.inputField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.util.SignUpInputValidation

@Composable
fun UsernameInputField(
    username: String,
    onValueChange: (String) -> Unit,
    onValidationSuccess: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(R.string.sign_up_username),
) {
    val context = LocalContext.current
    val supportingText by remember(username) {
        derivedStateOf {
            when {
                username.isBlank() -> ""
                !username.matches(SignUpInputValidation.USERNAME_LENGTH_REGEX.toRegex()) -> context.getString(R.string.sign_up_username_input_validation_length_message)
                !username.matches(SignUpInputValidation.USERNAME_REGEX.toRegex()) -> context.getString(R.string.sign_up_username_input_validation_message)
                else -> ""
            }
        }
    }

    LaunchedEffect(key1 = username, key2 = supportingText) {
        onValidationSuccess(username.isNotBlank() && supportingText.isBlank())
    }

    TextField(
        modifier = modifier
            .testTag("username")
            .fillMaxWidth(),
        value = username,
        onValueChange = onValueChange,
        label = { Text(hint) },
        singleLine = true,
        supportingText = { Text(supportingText) },
        isError = supportingText.isNotBlank(),
    )
}

@Preview
@Composable
private fun TextInputFieldPreview() {
    UsernameInputField(
        username = "",
        onValueChange = {},
        onValidationSuccess = {},
    )
}

@Preview
@Composable
private fun TextInputFieldPreviewWithLabel() {
    UsernameInputField(
        username = "",
        onValueChange = {},
        hint = "Username",
        onValidationSuccess = {},
    )
}

@Preview
@Composable
private fun TextInputFieldPreviewWithValue() {
    UsernameInputField(
        username = "username",
        onValueChange = {},
        onValidationSuccess = {},
    )
}

@Preview
@Composable
private fun TextInputFieldPreviewWithValueAndLabel() {
    UsernameInputField(
        username = "username",
        onValueChange = {},
        hint = "Username",
        onValidationSuccess = {},
    )
}
