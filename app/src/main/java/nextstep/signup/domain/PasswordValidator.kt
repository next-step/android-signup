package nextstep.signup.domain

enum class PasswordValidType {
    VALID,
    INVALID_PASSWORD_LENGTH, // password 길이가 유효하지 않음
    INVALID_PASSWORD_FORMAT, //password format이 일치하지 않음
}

object PasswordValidator {

    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    private val regex by lazy { PASSWORD_REGEX.toRegex() }

    fun match(password: String): PasswordValidType = when {
        password.length !in 8..16 -> PasswordValidType.INVALID_PASSWORD_LENGTH
        !password.matches(regex) -> PasswordValidType.INVALID_PASSWORD_FORMAT
        else -> PasswordValidType.VALID
    }
}