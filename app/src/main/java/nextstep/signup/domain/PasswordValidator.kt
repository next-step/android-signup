package nextstep.signup.domain

enum class PasswordValidType(val description: String) {
    VALID("유효함"),
    INVALID_LENGTH("password 길이가 유효하지 않음"),
    INVALID_REGEX("password format이 일치하지 않음"),
}

object PasswordValidator {

    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    private val regex by lazy { PASSWORD_REGEX.toRegex() }

    fun match(password: String): PasswordValidType = when {
        password.length !in 8..16 -> PasswordValidType.INVALID_LENGTH
        !password.matches(regex) -> PasswordValidType.INVALID_REGEX
        else -> PasswordValidType.VALID
    }
}