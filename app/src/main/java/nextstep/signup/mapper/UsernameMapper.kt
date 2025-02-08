package nextstep.signup.mapper

import nextstep.signup.state.UsernameState
import nextstep.signup.domain.Username
import nextstep.signup.domain.UsernameValidationResult
import nextstep.signup.domain.UsernameValidationResult.VALID
import nextstep.signup.domain.UsernameValidationResult.INVALID_LENGTH
import nextstep.signup.domain.UsernameValidationResult.INVALID_CHARACTER

fun Username.toUiState(): UsernameState {
    val validationResult: UsernameValidationResult = validate()

    return UsernameState(
        username = value,
        isError = validationResult != VALID,
        supportingText = when(validationResult) {
            VALID -> ""
            INVALID_LENGTH -> "이름은 2~5자여야 합니다."
            INVALID_CHARACTER -> "이름에는 숫자나 기호가 포함될 수 없습니다."
        }
    )
}