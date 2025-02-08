package nextstep.signup.mapper

import nextstep.signup.state.UsernameState
import nextstep.signup.domain.Username
import nextstep.signup.domain.UsernameValidationResult
import nextstep.signup.domain.UsernameValidationResult.Valid
import nextstep.signup.domain.UsernameValidationResult.InvalidLength
import nextstep.signup.domain.UsernameValidationResult.InvalidCharacter

fun Username.toUiState(): UsernameState {
    val result: UsernameValidationResult = validate()

    return UsernameState(
        username = this.value,
        isError = result != Valid,
        supportingText = when(result) {
            Valid -> ""
            InvalidLength -> "이름은 2~5자여야 합니다."
            InvalidCharacter -> "이름에는 숫자나 기호가 포함될 수 없습니다."
        }
    )
}