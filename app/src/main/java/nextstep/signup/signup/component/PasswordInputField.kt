package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.domain.InputPassword

@Composable
fun PasswordInputField(
    inputPassword: InputPassword? = null,
    onInputChanged: (InputPassword) -> Unit,
) {
    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_password),
        value = inputPassword?.text.orEmpty(),
        isInputPassword = true,
        onTextChanged = { onInputChanged(InputPassword(it)) },
        errorMessage = when {
            inputPassword == null || inputPassword.isValid() -> null
            inputPassword.isOutOfLength() -> stringResource(id = R.string.signup_input_text_error_message_password_length_out_of_range)
            inputPassword.hasNotAlphabetAndNumber() -> stringResource(id = R.string.signup_input_text_error_message_password_must_have_alphabet_and_number)
            else -> null
        }
    )
}
