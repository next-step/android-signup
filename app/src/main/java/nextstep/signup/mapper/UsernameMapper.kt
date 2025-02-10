package nextstep.signup.mapper

import nextstep.signup.state.UsernameState
import nextstep.signup.domain.Username
import nextstep.signup.domain.ValidationResult

fun Username.toUiState(): UsernameState {
    val validationResult: ValidationResult = validate()

    return UsernameState(
        username = value,
        isError = validationResult != ValidationResult.VALID,
        supportingText = when(validationResult) {
            ValidationResult.VALID -> ""
            ValidationResult.INVALID_LENGTH -> "이름은 2~5자여야 합니다."
            ValidationResult.INVALID_CHARACTER -> "이름에는 숫자나 기호가 포함될 수 없습니다."
            else -> ""
        }
    )
}
