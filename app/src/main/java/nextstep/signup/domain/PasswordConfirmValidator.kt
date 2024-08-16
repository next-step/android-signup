package nextstep.signup.domain


enum class PasswordConfirmValidType {
    VALID,
    INVALID_NOT_EQUAL, // 두 비밀번호가 일치하지 않음
}

object PasswordConfirmValidator {

    fun match(password: String, passwordConfirm: String): PasswordConfirmValidType =
        if (password == passwordConfirm) PasswordConfirmValidType.VALID
        else PasswordConfirmValidType.INVALID_NOT_EQUAL

}