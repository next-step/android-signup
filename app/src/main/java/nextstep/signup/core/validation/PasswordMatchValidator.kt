package nextstep.signup.core.validation

enum class PasswordMatchValidationResult {
    VALID,
    MISMATCH,
}

class PasswordMatchValidator(
    private val originalPassword: String
) : Validator<PasswordMatchValidationResult> {

    override fun validate(value: String): PasswordMatchValidationResult {
        return if (originalPassword == value) {
            PasswordMatchValidationResult.VALID
        } else {
            PasswordMatchValidationResult.MISMATCH
        }
    }
}
