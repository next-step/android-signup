package nextstep.signup.mapper

import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.domain.Email
import nextstep.signup.domain.ValidationResult
import nextstep.signup.state.InputFieldState

fun Email.toUiState(): InputFieldState {
    val validateResult: ValidationResult = validate()

    return InputFieldState(
        label = R.string.sign_up_email_label,
        input = value,
        keyboardType = KeyboardType.Email,
        isError = validateResult != ValidationResult.VALID && validateResult != ValidationResult.EMPTY_VALUE,
        supportingText = when (validateResult) {
            ValidationResult.VALID -> ""
            ValidationResult.INVALID_FORM -> "이메일 형식이 올바르지 않습니다."
            else -> ""
        }
    )
}
