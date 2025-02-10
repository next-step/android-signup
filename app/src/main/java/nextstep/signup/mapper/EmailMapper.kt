package nextstep.signup.mapper

import nextstep.signup.domain.Email
import nextstep.signup.domain.ValidationResult
import nextstep.signup.state.EmailState

fun Email.toUiState(): EmailState {
    val validateResult: ValidationResult = validate()

    return EmailState(
        email = value,
        isError = validateResult != ValidationResult.VALID,
        supportingText = when (validateResult) {
            ValidationResult.VALID -> ""
            ValidationResult.INVALID_FORM -> "이메일 형식이 올바르지 않습니다."
            else -> ""
        }
    )
}
