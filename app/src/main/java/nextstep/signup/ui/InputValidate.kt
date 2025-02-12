package nextstep.signup.ui

import nextstep.signup.R

private const val USERNAME_PATTERN = "^[a-zA-Z가-힣]+$"
private const val EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
private const val PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

private val usernameRegex = USERNAME_PATTERN.toRegex()
private val emailRegex = EMAIL_PATTERN.toRegex()
private val passwordRegex = PASSWORD_PATTERN.toRegex()

sealed class ValidationState {
    data class Error(val resourceId: Int) : ValidationState()
    data object None : ValidationState()
}

fun validateUsername(username: String): ValidationState {
    return if (username.length !in 2..5) {
        ValidationState.Error(R.string.error_username_length)
    } else if (!username.matches(usernameRegex)) {
        ValidationState.Error(R.string.error_username_format)
    } else {
        ValidationState.None
    }
}

fun validateEmail(email: String): ValidationState {
    return if (!email.matches(emailRegex)) {
        ValidationState.Error(R.string.error_email_format)
    } else {
        ValidationState.None
    }
}

fun validatePassword(password: String): ValidationState {
    return if (password.length !in 8..16) {
        ValidationState.Error(R.string.error_password_length)
    } else if (!password.matches(passwordRegex)) {
        ValidationState.Error(R.string.error_password_format)
    } else {
        ValidationState.None
    }
}

fun validatePasswordConfirm(password: String, passwordConfirm: String): ValidationState {
    return if (passwordConfirm != password) {
        ValidationState.Error(R.string.error_password_confirm_not_equal)
    } else {
        ValidationState.None
    }
}
