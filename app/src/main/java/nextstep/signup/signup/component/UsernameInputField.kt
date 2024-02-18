package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.domain.DefaultUsernameValidator
import nextstep.signup.domain.UsernameValidator

@Composable
fun UsernameInputField(
    value: String,
    onTextChanged: (String, isUsernameValid: Boolean) -> Unit,
    usernameValidator: UsernameValidator = DefaultUsernameValidator(),
) {
    val usernameValidationResult = usernameValidator.validate(value)

    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_username),
        value = value,
        onTextChanged = { onTextChanged(it, usernameValidator.validate(it).isValid()) },
        errorMessage = when {
            value.isEmpty()
                    || usernameValidationResult == UsernameValidator.Result.Valid -> null

            usernameValidationResult == UsernameValidator.Result.OutOfLength -> stringResource(id = R.string.signup_input_text_error_message_username_length_out_of_range)

            usernameValidationResult == UsernameValidator.Result.HasNumberOrSymbol ->
                stringResource(id = R.string.signup_input_text_error_message_username_has_number_or_symbol)

            else -> null
        }
    )
}
