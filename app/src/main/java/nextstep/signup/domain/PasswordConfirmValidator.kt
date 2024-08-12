package nextstep.signup.domain


enum class PasswordConfirmValidType(val description: String) {
    VALID("유효함"),
    INVALID_EQUAL("일치하지 않음")
}

object PasswordConfirmValidator {

    fun match(password: String, passwordConfirm: String): PasswordConfirmValidType =
        if (password == passwordConfirm) PasswordConfirmValidType.VALID
        else PasswordConfirmValidType.INVALID_EQUAL

}