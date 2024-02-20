package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.domain.InputEmail

@Composable
fun EmailInputField(
    inputEmail: InputEmail? = null,
    onInputChanged: (InputEmail) -> Unit,
) {
    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_email),
        value = inputEmail?.text.orEmpty(),
        onTextChanged = { onInputChanged(InputEmail(it)) },
        errorMessage = when {
            inputEmail == null || inputEmail.isValid -> null
            inputEmail.isInvalid -> stringResource(id = R.string.signup_input_text_error_message_email_is_invalid)
            else -> null
        }
    )
}
