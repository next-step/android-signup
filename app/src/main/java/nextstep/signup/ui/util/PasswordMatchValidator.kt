package nextstep.signup.ui.util

class PasswordMatchValidator(
    private val getEnteredPassword: () -> String = { "" },
) : Validator {

    override fun validate(value: String): ValidationResult {
        return when {
            value != getEnteredPassword() -> ValidationResult(
                ValidationStates.PASSWORD_MISMATCH_ERROR,
            )

            else -> ValidationResult(ValidationStates.SUCCESS)
        }
    }
}
