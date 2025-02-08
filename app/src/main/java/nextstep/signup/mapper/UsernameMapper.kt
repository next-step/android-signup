package nextstep.signup.mapper

import nextstep.signup.state.UsernameState
import nextstep.signup.domain.Username
import nextstep.signup.domain.UsernameValidationResult
import nextstep.signup.domain.UsernameValidationResult.Valid
import nextstep.signup.domain.UsernameValidationResult.InvalidLength
import nextstep.signup.domain.UsernameValidationResult.InvalidCharacter

fun Username.toUiState(): UsernameState {
    val validationResult: UsernameValidationResult = validate()

    return UsernameState(
        username = value,
        isError = validationResult != Valid,
        supportingText = when(validationResult) {
            Valid -> ""
            InvalidLength -> "이름은 2~5자여야 합니다."
            InvalidCharacter -> "이름에는 숫자나 기호가 포함될 수 없습니다."
        }
    )
}