package nextstep.signup.component.textField


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.UserNameError
import nextstep.signup.model.UserNameState
import nextstep.signup.valid.RegexBasedSignUpValidator
import nextstep.signup.valid.SignUpValidator

@Composable
fun UserNameTextField(
    userName: String,
    onUserNameChange: (String) -> Unit,
    onValidationResult: (Boolean) -> Unit,
    validator: SignUpValidator = RegexBasedSignUpValidator()
) {
    val userNameState by remember(userName) {
        derivedStateOf { validator.validateUsername(userName) }
    }
    LaunchedEffect(userNameState) {
        onValidationResult(userNameState is UserNameState.Valid)
    }
    BaseSignUpTextField(
        text = userName,
        onValueChange = onUserNameChange,
        label = stringResource(R.string.sign_up_text_field_username),
        isError = userNameState is UserNameState.Invalid,
        errorMessage = when (userNameState) {
            is UserNameState.Invalid -> when ((userNameState as UserNameState.Invalid).error) {
                UserNameError.Length -> stringResource(R.string.sign_up_error_name_length)
                UserNameError.InvalidCharacters -> stringResource(R.string.sign_up_error_name_invalid_chars)
            }
            UserNameState.Valid, UserNameState.Initial -> ""
        }
    )
}

@Preview(showBackground = true)
@Composable
fun UserNameTextFieldPreview() {
    UserNameTextField(
        userName = "JohnDoe",
        onUserNameChange = {},
        onValidationResult = {}
    )
}