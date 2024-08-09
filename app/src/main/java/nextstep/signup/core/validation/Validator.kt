package nextstep.signup.core.validation

interface Validator {
    fun validate(value: String): ValidationResult
}