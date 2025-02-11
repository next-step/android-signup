package nextstep.signup.ui.util

class PasswordValidator : Validator {

    override fun validate(value: String): ValidationResult {
        return when {
            value.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH -> ValidationResult(
                ValidationStates.PASSWORD_LENGTH_ERROR,
            )

            !value.matches(PASSWORD_REGEX) -> ValidationResult(
                ValidationStates.PASSWORD_COMPLEXITY_ERROR,
            )

            else -> ValidationResult(ValidationStates.SUCCESS)
        }
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
        private const val MAX_PASSWORD_LENGTH = 16

        private val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()
    }
}
