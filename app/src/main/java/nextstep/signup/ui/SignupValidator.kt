package nextstep.signup.ui

object SignupValidator {
    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun isValidUsername(username: String) = username.matches(Regex(USERNAME_REGEX))
    fun isValidEmail(email: String) = email.matches(Regex(EMAIL_REGEX))
    fun isValidPassword(password: String) = password.matches(Regex(PASSWORD_REGEX))
}