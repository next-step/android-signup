package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.util.ValidationPatterns.USERNAME_REGEX

@Composable
fun UserNameTextField(
    username: String,
    onNameChange: (String) -> Unit,
    onValidationStateChanged: (Boolean) -> Unit
) {

    val validationResult = validateUserName(username)
    val errorMessage = when (validationResult) {
        UserNameValidationResult.Empty -> ""
        UserNameValidationResult.InvalidSize -> stringResource(id = R.string.sign_up_username_size_error)
        UserNameValidationResult.InvalidFormat -> stringResource(id = R.string.sign_up_username_format_error)
        else -> ""
    }

    LaunchedEffect(key1 = username, key2 = errorMessage) {
        onValidationStateChanged(username.isNotBlank() && errorMessage.isBlank())
    }

    InputTextField(
        modifier = Modifier.padding(top = 6.dp),
        value = username,
        onValueChange = onNameChange,
        label = stringResource(R.string.sign_up_label_username),
        errorMessage = errorMessage
    )
}

private fun validateUserName(username: String): UserNameValidationResult {
    return when {
        username.isEmpty() -> UserNameValidationResult.Empty
        username.length !in 2..5 -> UserNameValidationResult.InvalidSize
        !username.matches(Regex(USERNAME_REGEX)) -> UserNameValidationResult.InvalidFormat
        else -> UserNameValidationResult.Valid
    }
}

sealed class UserNameValidationResult {
    data object Valid : UserNameValidationResult()
    data object Empty : UserNameValidationResult()
    data object InvalidSize : UserNameValidationResult()
    data object InvalidFormat : UserNameValidationResult()
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview() {
    UserNameTextField(username = "김은혜", {}, {})
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldErrorPreview() {
    UserNameTextField(username = "김은혜!", {}, {})
}
