package nextstep.signup.ui

import nextstep.signup.R

private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

sealed class ValidationState {
    data class Error(val resourceId: Int) : ValidationState()
    data object Success : ValidationState()
}

fun validateUsername(username: String): ValidationState {
    return if (username.length !in 2..5) {
        ValidationState.Error(R.string.error_username_length)
    } else if (!username.matches(Regex(USERNAME_REGEX))) {
        ValidationState.Error(R.string.error_username_format)
    } else {
        ValidationState.Success
    }
}

fun validateEmail(email: String): ValidationState {
    return if (!email.matches(Regex(EMAIL_REGEX))) {
        ValidationState.Error(R.string.error_email_format)
    } else {
        ValidationState.Success
    }
}

fun validatePassword(password: String): ValidationState {
    return if (password.length !in 8..16) {
        ValidationState.Error(R.string.error_password_length)
    } else if (!password.matches(Regex(PASSWORD_REGEX))) {
        ValidationState.Error(R.string.error_password_format)
    } else {
        ValidationState.Success
    }
}

fun validatePasswordConfirm(password: String, passwordConfirm: String): ValidationState {
    return if (passwordConfirm != password) {
        ValidationState.Error(R.string.error_password_confirm_not_equal)
    } else {
        ValidationState.Success
    }
}
