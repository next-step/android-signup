package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.domain.InputUsername

@Composable
fun UsernameInputField(
    inputUsername: InputUsername? = null,
    onInputChanged: (InputUsername) -> Unit,
) {
    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_username),
        value = inputUsername?.text.orEmpty(),
        onTextChanged = { onInputChanged(InputUsername(it)) },
        errorMessage = when {
            inputUsername == null || inputUsername.isValid() -> null
            inputUsername.isOutOfLength() -> stringResource(id = R.string.signup_input_text_error_message_username_length_out_of_range)
            inputUsername.hasNumberOfSymbol() -> stringResource(id = R.string.signup_input_text_error_message_username_has_number_or_symbol)
            else -> null
        }
    )
}
