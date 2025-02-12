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
        isError = validationResult is ValidationResult.Username,
        supportingText = if (validationResult is ValidationResult.Username) {
            when (validationResult) {
                ValidationResult.Username.INVALID_LENGTH -> "이름은 2~5자여야 합니다."
                ValidationResult.Username.INVALID_CHARACTER -> "이름에는 숫자나 기호가 포함될 수 없습니다."
            }
        } else "",
    )
}
