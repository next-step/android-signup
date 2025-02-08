package nextstep.signup.mapper

import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordValidationResult
import nextstep.signup.domain.PasswordValidationResult.VALID
import nextstep.signup.domain.PasswordValidationResult.INVALID_LENGTH
import nextstep.signup.domain.PasswordValidationResult.INVALID_CHARACTER
import nextstep.signup.state.PasswordState

fun Password.toUiState(): PasswordState {
    val validationResult: PasswordValidationResult = validate()

    return PasswordState(
        password = value,
        isError = validationResult != VALID,
        supportingText = when(validationResult) {
            VALID -> ""
            INVALID_LENGTH -> "비밀번호는 8~16자여야 합니다."
            INVALID_CHARACTER -> "비밀번호는 영문과 숫자를 포함해야 합니다."
        }
    )
}