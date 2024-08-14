package nextstep.signup.domain

enum class UsernameValidType(val description: String) {
    VALID("유효함"),
    INVALID_LENGTH("Username 길이가 유효하지 않음"),
    INVALID_REGEX("Username format이 일치하지 않음"),
}

object UsernameValidator {
    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private val regex by lazy { USERNAME_REGEX.toRegex() }

    fun match(username: String): UsernameValidType = when {
        username.length !in 2..5 -> UsernameValidType.INVALID_LENGTH
        !username.matches(regex) -> UsernameValidType.INVALID_REGEX
        else -> UsernameValidType.VALID
    }

}