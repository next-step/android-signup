package nextstep.signup.mapper

import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.domain.Password
import nextstep.signup.domain.ValidationResult
import nextstep.signup.state.InputFieldState

fun Password.toUiState(): InputFieldState {
    val validationResult: ValidationResult = validate()

    return InputFieldState(
        label = R.string.sign_up_password_label,
        input = value,
        keyboardType = KeyboardType.Password,
        needHide = true,
        isError = validationResult is ValidationResult.Password,
        supportingText = if (validationResult is ValidationResult.Password) {
            when (validationResult) {
                ValidationResult.Password.INVALID_LENGTH -> "비밀번호는 8~16자여야 합니다."
                ValidationResult.Password.INVALID_CHARACTER -> "비밀번호는 영문과 숫자를 포함해야 합니다."
            }
        } else "",
    )
}
