package nextstep.signup.ui

import nextstep.signup.R

private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

fun validateUsername(username: String): Int? {
    return if (username.length !in 2..5) {
        R.string.error_username_length
    } else if (!username.matches(Regex(USERNAME_REGEX))) {
        R.string.error_username_format
    } else {
        null
    }
}

fun validateEmail(email: String): Int? {
    return if (!email.matches(Regex(EMAIL_REGEX))) {
        R.string.error_email_format
    } else {
        null
    }
}

fun validatePassword(password: String): Int? {
    return if (password.length !in 8..16) {
        R.string.error_password_length
    } else if (!password.matches(Regex(PASSWORD_REGEX))) {
        R.string.error_password_format
    } else {
        null
    }
}

fun validatePasswordConfirm(password: String, passwordConfirm: String): Int? {
    return if (passwordConfirm != password) {
        R.string.error_password_confirm_not_equal
    } else {
        null
    }
}
