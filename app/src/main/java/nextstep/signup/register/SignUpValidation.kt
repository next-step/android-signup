package nextstep.signup.register

object SignUpValidation {

    const val USER_LENGTH_REGEX = "^.{2,5}$"
    const val PASSWORD_LENGTH_REGEX = "^.{8,16}$"

    const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    const val PASSWORD_REGEX = "^(?=.*[a-zA-Z가-힣])(?=.*[0-9]).*$"

    fun isValidUserName(name: String): Boolean =
        name.matches(USER_LENGTH_REGEX.toRegex()) &&
                name.matches(USERNAME_REGEX.toRegex())

    fun isValidEmail(email: String): Boolean =
        email.matches(EMAIL_REGEX.toRegex())

    fun isValidPassword(password: String): Boolean =
        password.matches(PASSWORD_LENGTH_REGEX.toRegex()) &&
                password.matches(PASSWORD_REGEX.toRegex())

    fun isValidPasswordConfirm(password: String, passwordConfirm: String): Boolean =
        password == passwordConfirm

    fun isAllValid(
        userName: String,
        email: String,
        password: String,
        passwordConfirm: String
    ) =
        isValidUserName(userName) &&
                isValidEmail(email) &&
                isValidPassword(password) &&
                isValidPasswordConfirm(password, passwordConfirm)

}