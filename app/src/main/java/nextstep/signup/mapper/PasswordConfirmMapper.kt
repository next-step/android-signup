package nextstep.signup.mapper

import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.ValidationResult
import nextstep.signup.state.InputFieldState

fun PasswordConfirm.toUiState(): InputFieldState {
    val validationResult: ValidationResult = validate()

    return InputFieldState(
        label = R.string.sign_up_password_confirm_label,
        input = value,
        keyboardType = KeyboardType.Password,
        needHide = true,
        isError = validationResult != ValidationResult.VALID &&
                validationResult != ValidationResult.EMPTY_VALUE &&
                password.isNotEmpty(),
        supportingText = when (validationResult) {
            ValidationResult.VALID -> ""
            ValidationResult.NOT_SAME -> "비밀번호가 일치하지 않습니다."
            else -> ""
        }
    )
}
