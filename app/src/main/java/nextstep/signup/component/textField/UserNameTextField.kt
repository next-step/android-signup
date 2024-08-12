package nextstep.signup.component.textField


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.UserNameError
import nextstep.signup.model.UserNameState

@Composable
fun UserNameTextField(
    userName: String,
    onUserNameChange: (String) -> Unit,
    userNameState: UserNameState
) {
    BaseSignUpTextField(
        text = userName,
        onValueChange = onUserNameChange,
        label = stringResource(R.string.sign_up_text_field_username),
        isError = userNameState is UserNameState.Invalid,
        errorMessage = when (userNameState) {
            is UserNameState.Invalid -> when (userNameState.error) {
                UserNameError.Length -> stringResource(R.string.sign_up_error_name_length)
                UserNameError.InvalidCharacters -> stringResource(R.string.sign_up_error_name_invalid_chars)
            }
            UserNameState.Valid, UserNameState.Initial -> ""
        }
    )
}
