package nextstep.signup.domain

enum class EmailValidType {
    VALID,
    INVALID_EMAIL_FORMAT, // Email format이 일치하지 않음
}

object EmailValidator {

    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private val regex by lazy { EMAIL_REGEX.toRegex() }

    fun match(email: String): EmailValidType = when {
        !email.matches(regex) -> EmailValidType.INVALID_EMAIL_FORMAT
        else -> EmailValidType.VALID
    }

}