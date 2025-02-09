package nextstep.signup.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.utils.UserNameErrorType
import nextstep.signup.utils.SignUpValidator

@Composable
fun UserNameTextField(
    userName: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val errorType by remember(userName) {
        derivedStateOf { SignUpValidator.validateUserName(userName) }
    }

    SignUpBasicTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_username),
        value = userName,
        errorMessage = when (errorType) {
            UserNameErrorType.USERNAME_LENGTH_ERROR -> stringResource(R.string.signup_username_length_error)
            UserNameErrorType.USERNAME_FORMAT_ERROR -> stringResource(R.string.signup_username_format_error)
            else -> ""
        },
        onValueChange = {
            onValueChange(it)
        }
    )
}