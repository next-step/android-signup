package nextstep.signup

object InputValidation {

    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun validateUserName(userName: String): Int? {
        return if (userName.length !in 2..5) {
            R.string.username_length_error
        } else if (!userName.matches(Regex(USERNAME_REGEX))) {
            R.string.username_invalid_characters_error
        } else null
    }

    fun validateEmail(email: String): Int? {
        return  if (!email.matches(Regex(EMAIL_REGEX))) {
            R.string.email_invalid_format_error
        } else null
    }

    fun validatePassword(password: String): Int? {
        return if (password.length !in 8..16) {
            R.string.password_length_error
        } else if (!password.matches(Regex(PASSWORD_REGEX))) {
            R.string.password_contain_characters_error
        } else null
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Int? {
        return if (password != confirmPassword) {
            R.string.password_mismatch_error
        } else null
    }
}