package nextstep.signup.domain

enum class PasswordConfirmValidationResult {
    SUCCESS,
    INVALID,
    ;

    companion object {
        fun match(password: String, confirmPassword: String): PasswordConfirmValidationResult =
            if (password == confirmPassword) {
                SUCCESS
            } else {
                INVALID
            }
    }
}
