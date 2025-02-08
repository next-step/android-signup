package nextstep.signup.mapper

import nextstep.signup.domain.Email
import nextstep.signup.domain.EmailValidationResult
import nextstep.signup.domain.EmailValidationResult.Valid
import nextstep.signup.domain.EmailValidationResult.InvalidForm
import nextstep.signup.state.EmailState

fun Email.toUiState(): EmailState {
    val validateResult: EmailValidationResult = validate()

    return EmailState(
        email = value,
        isError = validateResult != Valid,
        supportingText = when (validateResult) {
            Valid -> ""
            InvalidForm -> "이메일 형식이 올바르지 않습니다."
        }
    )
}