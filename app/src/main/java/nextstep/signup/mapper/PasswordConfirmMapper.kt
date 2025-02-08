package nextstep.signup.mapper

import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.PasswordConfirmValidationResult
import nextstep.signup.domain.PasswordConfirmValidationResult.VALID
import nextstep.signup.domain.PasswordConfirmValidationResult.NOT_SAME
import nextstep.signup.state.PasswordConfirmState

fun PasswordConfirm.toUiState(
    password: String
): PasswordConfirmState {
    val validationResult: PasswordConfirmValidationResult = validate(password)

    return PasswordConfirmState(
        passwordConfirm = value,
        isError = validationResult != VALID,
        supportingText = when (validationResult) {
            VALID -> ""
            NOT_SAME -> "비밀번호가 일치하지 않습니다."
        }
    )
}