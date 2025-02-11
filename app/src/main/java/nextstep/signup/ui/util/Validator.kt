package nextstep.signup.ui.util

import nextstep.signup.R

interface Validator {
    fun validate(value: String): ValidationResult
}

data class ValidationResult(
    @ValidationState val validationState: String = ValidationStates.SUCCESS,
) {
    fun isValid(): Boolean = validationState == ValidationStates.SUCCESS

    fun getErrorMessage() = validationState.getErrorMessage()
}

annotation class ValidationState

object ValidationStates {
    const val SUCCESS = "success"
    const val NAME_LENGTH_ERROR = "name_length_error"
    const val NAME_INVALID_CHARACTER = "name_invalid_character"
    const val EMAIL_FORMAT_ERROR = "email_format_error"
    const val PASSWORD_LENGTH_ERROR = "password_length_error"
    const val PASSWORD_COMPLEXITY_ERROR = "password_complexity_error"
    const val PASSWORD_MISMATCH_ERROR = "password_mismatch_error"
}

fun String.getErrorMessage(): Int? {
    return when (this) {
        ValidationStates.NAME_LENGTH_ERROR -> R.string.signup_name_length_error_message
        ValidationStates.NAME_INVALID_CHARACTER -> R.string.signup_name_invalid_character_error_message
        ValidationStates.EMAIL_FORMAT_ERROR -> R.string.signup_email_format_error_message
        ValidationStates.PASSWORD_LENGTH_ERROR -> R.string.signup_password_length_error_message
        ValidationStates.PASSWORD_COMPLEXITY_ERROR -> R.string.signup_password_complexity_error_message
        ValidationStates.PASSWORD_MISMATCH_ERROR -> R.string.signup_password_mismatch_error_message
        else -> null
    }
}


