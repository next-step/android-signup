package nextstep.signup.core.validation

class NameValidator : Validator {
    override fun validate(value: String): ValidationResult {
        if (value.length !in 2..5) {
            return ValidationResult(false, "이름은 2~5자여야 합니다.")
        }
        if (value.any { it.isDigit() || it.isLetter().not() }) {
            return ValidationResult(false, "이름에는 숫자나 기호가 포함될 수 없습니다.")
        }
        return ValidationResult(true)
    }
}
