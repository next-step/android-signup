package nextstep.signup.core.validation

class PasswordMatchValidator(private val originalPassword: String) : Validator {
    override fun validate(value: String): ValidationResult {
        return if (value == originalPassword) {
            ValidationResult(true)
        } else {
            ValidationResult(false, "비밀번호가 일치하지 않습니다.")
        }
    }
}
