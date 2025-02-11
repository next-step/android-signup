package nextstep.signup.domain

enum class ValidationResult {
    VALID,
    INVALID_FORM,
    INVALID_LENGTH,
    INVALID_CHARACTER,
    NOT_SAME,
    EMPTY_VALUE,
}
