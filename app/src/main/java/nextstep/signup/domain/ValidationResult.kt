package nextstep.signup.domain

sealed interface ValidationResult {
    data object Valid: ValidationResult
    data object EmptyValue: ValidationResult

    enum class Username: ValidationResult {
        INVALID_LENGTH,
        INVALID_CHARACTER,
    }

    enum class Email: ValidationResult {
        INVALID_FORM,
    }

    enum class Password: ValidationResult {
        INVALID_LENGTH,
        INVALID_CHARACTER,
    }

    enum class PasswordConfirm: ValidationResult {
        NOT_SAME,
    }
}
