package nextstep.signup.mapper

import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.ValidationResult
import nextstep.signup.state.PasswordConfirmState

fun PasswordConfirm.toUiState(): PasswordConfirmState {
    val validationResult: ValidationResult = validate()

    return PasswordConfirmState(
        passwordConfirm = value,
        isError = validationResult != ValidationResult.VALID,
        supportingText = when (validationResult) {
            ValidationResult.VALID -> ""
            ValidationResult.NOT_SAME -> "비밀번호가 일치하지 않습니다."
            else -> ""
        }
    )
}
