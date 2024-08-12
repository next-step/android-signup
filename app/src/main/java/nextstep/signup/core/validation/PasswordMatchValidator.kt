package nextstep.signup.core.validation

enum class PasswordMatchValidationResult {
    VALID,
    MISMATCH,
}

class PasswordMatchValidator {

    fun validate(
        confirmPassword: String,
        password: String
    ): PasswordMatchValidationResult {
        return if (password == confirmPassword) {
            PasswordMatchValidationResult.VALID
        } else {
            PasswordMatchValidationResult.MISMATCH
        }
    }
}
