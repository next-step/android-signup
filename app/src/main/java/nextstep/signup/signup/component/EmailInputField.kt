package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.domain.DefaultEmailValidator
import nextstep.signup.domain.EmailValidator

@Composable
fun EmailInputField(
    value: String,
    onTextChanged: (String, isEmailValid: Boolean) -> Unit,
    emailValidator: EmailValidator = DefaultEmailValidator(),
) {
    val emailValidationResult = emailValidator.validate(value)

    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_email),
        value = value,
        onTextChanged = { onTextChanged(it, emailValidationResult.isValid()) },
        errorMessage = when {
            value.isEmpty() || emailValidationResult.isValid() -> null
            emailValidationResult.isInvalid() -> stringResource(id = R.string.signup_input_text_error_message_email_is_invalid)
            else -> null
        }
    )
}
