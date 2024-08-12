package nextstep.signup.domain

enum class EmailValidType(val description: String) {
    VALID("유효함"),
    INVALID_REGEX("Email format이 일치하지 않음"),
}

object EmailValidator {

    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

    fun match(email: String): EmailValidType = when {
        !email.matches(Regex(EMAIL_REGEX)) -> EmailValidType.INVALID_REGEX
        else -> EmailValidType.VALID
    }

}