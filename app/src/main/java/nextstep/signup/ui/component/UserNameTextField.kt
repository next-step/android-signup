package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.util.ValidationPatterns.USERNAME_REGEX

@Composable
fun UserNameTextField(
    username: String,
    onNameChange: (String) -> Unit
) {
    InputTextField(
        modifier = Modifier.padding(top = 6.dp),
        value = username,
        onValueChange = onNameChange,
        label = stringResource(R.string.sign_up_label_username),
        errorMessage = getUserNameErrorMessage(username)
    )
}

@Composable
fun getUserNameErrorMessage(username: String): String {
    return when {
        username.isEmpty() -> ""
        username.length !in 2..5 -> {
            stringResource(id = R.string.sign_up_username_size_error)
        }
        !username.matches(Regex(USERNAME_REGEX)) -> {
            stringResource(id = R.string.sign_up_username_format_error)
        }
        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun UserNameTextFieldPreview() {
    UserNameTextField(username = "김은혜") {}
}

@Preview(showBackground = true)
@Composable
fun UserNameTextFieldErrorPreview() {
    UserNameTextField(username = "김은혜!") {}
}


