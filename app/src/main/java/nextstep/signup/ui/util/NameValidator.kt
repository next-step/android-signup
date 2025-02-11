package nextstep.signup.ui.util

class NameValidator : Validator {

    override fun validate(value: String): ValidationResult {
        return when {
            value.length !in MIN_NAME_LENGTH..MAX_NAME_LENGTH -> ValidationResult(
                ValidationStates.NAME_LENGTH_ERROR,
            )

            !value.matches(USERNAME_REGEX) -> ValidationResult(
                ValidationStates.NAME_INVALID_CHARACTER,
            )

            else -> ValidationResult(ValidationStates.SUCCESS)
        }
    }

    companion object {
        private const val MIN_NAME_LENGTH = 2
        private const val MAX_NAME_LENGTH = 5

        private val USERNAME_REGEX = "^[a-zA-Z가-힣]+$".toRegex()
    }
}