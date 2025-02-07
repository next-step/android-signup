package nextstep.signup.mapper

import nextstep.signup.state.UsernameState
import nextstep.signup.domain.Username
import nextstep.signup.domain.UsernameValidationResult

fun Username.toUiState(): UsernameState {
    val result: UsernameValidationResult = validate()

    return UsernameState(
        username = this.value,
        isError = result != UsernameValidationResult.Valid,
        supportingText = when(result) {
            UsernameValidationResult.Valid -> ""
            UsernameValidationResult.InvalidLength -> "이름은 2~5자여야 합니다."
            UsernameValidationResult.InvalidCharacter -> "이름에는 숫자나 기호가 포함될 수 없습니다."
        }
    )
}