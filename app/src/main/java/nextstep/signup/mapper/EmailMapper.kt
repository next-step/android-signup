package nextstep.signup.mapper

import nextstep.signup.domain.Email
import nextstep.signup.domain.EmailValidationResult
import nextstep.signup.domain.EmailValidationResult.VALID
import nextstep.signup.domain.EmailValidationResult.INVALID_FORM
import nextstep.signup.state.EmailState

fun Email.toUiState(): EmailState {
    val validateResult: EmailValidationResult = validate()

    return EmailState(
        email = value,
        isError = validateResult != VALID,
        supportingText = when (validateResult) {
            VALID -> ""
            INVALID_FORM -> "이메일 형식이 올바르지 않습니다."
        }
    )
}
