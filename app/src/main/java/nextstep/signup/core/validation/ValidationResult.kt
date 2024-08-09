package nextstep.signup.core.validation

data class ValidationResult(
    val isValid: Boolean,
    val message: String = ""
)
