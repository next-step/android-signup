package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.component.PasswordConfirmValidationResult.Empty
import nextstep.signup.ui.component.PasswordConfirmValidationResult.Mismatch
import nextstep.signup.ui.component.PasswordConfirmValidationResult.Valid
import nextstep.signup.ui.component.UserNameValidationResult.Companion.validateUserName
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
        modifier = Modifier
            .testTag("username")
            .padding(top = 6.dp),
        value = username,
        onValueChange = onNameChange,
        label = stringResource(R.string.sign_up_label_username),
        errorMessage = errorMessage
    )
}

enum class UserNameValidationResult {
    Valid,
    Empty,
    InvalidSize,
    InvalidFormat;

    companion object {
        fun validateUserName(username: String): UserNameValidationResult {
            return when {
                username.isEmpty() -> Empty
                username.length !in 2..5 -> InvalidSize
                !username.matches(Regex(USERNAME_REGEX)) -> InvalidFormat
                else -> Valid
            }
        }
    }
}
class UserNamePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "김은혜",
        "김은혜!",
        "김"
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview(
    @PreviewParameter(UserNamePreviewParameterProvider::class) userName: String
) {
    UserNameTextField(username = userName, {}, {})
}
