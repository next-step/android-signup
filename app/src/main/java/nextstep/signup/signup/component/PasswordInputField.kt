package nextstep.signup.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.domain.DefaultPasswordValidator
import nextstep.signup.domain.PasswordValidator

@Composable
fun PasswordInputField(
    value: String,
    onTextChanged: (String, isPasswordValid: Boolean) -> Unit,
    passwordValidator: PasswordValidator = DefaultPasswordValidator(),
) {
    val passwordValidationResult = remember(value) {
        passwordValidator.validate(value)
    }
    SignUpInputTextField(
        labelName = stringResource(id = R.string.signup_input_text_label_password),
        value = value,
        isInputPassword = true,
        onTextChanged = { onTextChanged(it, passwordValidationResult.isValid()) },
        errorMessage = when {
            value.isEmpty()
                    || passwordValidationResult == PasswordValidator.Result.Valid -> null

            passwordValidationResult == PasswordValidator.Result.OutOfLength -> stringResource(id = R.string.signup_input_text_error_message_password_length_out_of_range)

            passwordValidationResult == PasswordValidator.Result.HasNotAlphabetAndNumber ->
                stringResource(id = R.string.signup_input_text_error_message_password_must_have_alphabet_and_number)

            else -> null
        }
    )
}
