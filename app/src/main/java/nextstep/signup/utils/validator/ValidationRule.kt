package nextstep.signup.utils.validator

data class ValidationRule(
    val regex: Regex,
    val errorMessageResId: Int,
    val errorType: ErrorType,
) {
    enum class ErrorType {
        OUT_OF_LENGTH_RANGE,
        LACK_OF_CHARACTER_TYPE,
        INVALID_FORM,
        INVALID_CHARACTER_TYPE_INCLUDED,
        NOT_EQUAL_VALUES,
    }
}