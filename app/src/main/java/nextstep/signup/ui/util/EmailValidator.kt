package nextstep.signup.ui.util

class EmailValidator : Validator {

    override fun validate(value: String): ValidationResult {
        return if (!value.matches(Regex(EMAIL_REGEX))) {
            ValidationResult(ValidationStates.EMAIL_FORMAT_ERROR)
        } else {
            ValidationResult(ValidationStates.SUCCESS)
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    }
}
