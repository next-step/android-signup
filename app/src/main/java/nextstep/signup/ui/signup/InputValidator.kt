package nextstep.signup.ui.signup

class InputValidator {

    private val userNameRegex = Regex(USERNAME_REGEX)
    private val emailRegex = Regex(EMAIL_REGEX)
    private val passwordRegex = Regex(PASSWORD_REGEX)

    fun checkUserName(userName: String) = userName.matches(userNameRegex)

    fun checkEmail(email: String) = email.matches(emailRegex)

    fun checkPassword(password: String) = password.matches(passwordRegex)


    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }
}
