package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

@Composable
fun PasswordConfirmInputField(
    value: String,
    passwordToCompare: String,
    onTextChanged: (String, isPasswordMatched: Boolean) -> Unit,
) {
    val isPasswordMatched = value == passwordToCompare

    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_password_confirm),
        value = value,
        isInputPassword = true,
        onTextChanged = { onTextChanged(it, it == passwordToCompare) },
        errorMessage = when {
            value.isEmpty() || isPasswordMatched -> null
            else -> stringResource(id = R.string.signup_input_text_error_message_password_confirm_not_matched)
        }
    )
}
