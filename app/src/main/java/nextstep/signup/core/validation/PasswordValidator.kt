package nextstep.signup.core.validation

class PasswordValidator : Validator {
    override fun validate(value: String): ValidationResult {
        if (value.length !in 8..16) {
            return ValidationResult(false, "비밀번호는 8~16자여야 합니다.")
        }
        if (!value.any { it.isDigit() } || !value.any { it.isLetter() }) {
            return ValidationResult(false, "비밀번호는 영문과 숫자를 포함해야 합니다.")
        }
        return ValidationResult(true)
    }
}
