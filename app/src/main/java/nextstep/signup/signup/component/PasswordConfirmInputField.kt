package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

@Composable
fun PasswordConfirmInputField(
    value: String,
    passwordToCompare: String,
    onTextChanged: (String) -> Unit,
    onPasswordMatched: (Boolean) -> Unit,
) {
    val isPasswordMatched = remember(value, passwordToCompare) {
        (value == passwordToCompare)
            .also(onPasswordMatched)
    }

    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_password_confirm),
        value = value,
        isInputPassword = true,
        onTextChanged = onTextChanged,
        errorMessage = when {
            value.isEmpty() || isPasswordMatched -> null
            else -> stringResource(id = R.string.signup_input_text_error_message_password_confirm_not_matched)
        }
    )
}
