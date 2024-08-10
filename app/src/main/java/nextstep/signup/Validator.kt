package nextstep.signup

object Validator {
    fun isValidUsername(username: String): Boolean {
        return username.matches(Regex(ValidationRule.USERNAME_REGEX))
    }

    fun isValidEmail(username: String): Boolean {
        return username.matches(Regex(ValidationRule.EMAIL_REGEX))
    }

    fun isValidPassword(username: String): Boolean {
        return username.matches(Regex(ValidationRule.PASSWORD_REGEX))
    }
}