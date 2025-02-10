package nextstep.signup.mapper

import nextstep.signup.domain.Password
import nextstep.signup.domain.ValidationResult
import nextstep.signup.state.PasswordState

fun Password.toUiState(): PasswordState {
    val validationResult: ValidationResult = validate()

    return PasswordState(
        password = value,
        isError = validationResult != ValidationResult.VALID,
        supportingText = when(validationResult) {
            ValidationResult.VALID -> ""
            ValidationResult.INVALID_LENGTH -> "비밀번호는 8~16자여야 합니다."
            ValidationResult.INVALID_CHARACTER -> "비밀번호는 영문과 숫자를 포함해야 합니다."
            else -> ""
        }
    )
}
