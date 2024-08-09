package nextstep.signup.core.validation

class EmailValidator : Validator {
    override fun validate(value: String): ValidationResult {
        return if (EMAIL_PATTERN.matches(value)) {
            ValidationResult(true)
        } else {
            ValidationResult(false, "이메일 형식이 올바르지 않습니다.")
        }
    }

    companion object {
        private val EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$".toRegex()
    }
}
