package nextstep.signup.mapper

import nextstep.signup.R
import nextstep.signup.domain.Username
import nextstep.signup.domain.ValidationResult
import nextstep.signup.state.InputFieldState

fun Username.toUiState(): InputFieldState {
    val validationResult: ValidationResult = validate()

    return InputFieldState(
        label = R.string.sign_up_username_label,
        input = value,
        isError = validationResult != ValidationResult.VALID && validationResult != ValidationResult.EMPTY_VALUE,
        supportingText = when (validationResult) {
            ValidationResult.VALID -> ""
            ValidationResult.INVALID_LENGTH -> "이름은 2~5자여야 합니다."
            ValidationResult.INVALID_CHARACTER -> "이름에는 숫자나 기호가 포함될 수 없습니다."
            else -> ""
        }
    )
}
