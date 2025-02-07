package nextstep.signup.ui.signup

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.R

data class SignUpState(
    val userName: InputState = InputState(
        label = R.string.username_label,
        onValueChange = {
            SignUpAction.OnUsernameChanged(it)
        }
    ),
    val email: InputState = InputState(
        label = R.string.email_label,
        onValueChange = {
            SignUpAction.OnEmailChanged(it)
        }
    ),
    val password: InputState = InputState(
        label = R.string.password_label,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = {
            SignUpAction.OnPasswordChanged(it)
        }
    ),
    val passwordConfirm: InputState = InputState(
        label = R.string.password_confirm_label,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = {
            SignUpAction.OnPasswordConfirmChanged(it)
        }
    ),
) {
    fun getInputStateList() = listOf(userName, email, password, passwordConfirm)
}

data class InputState(
    @StringRes val label: Int? = null,
    val value: String = "",
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val onValueChange: (String) -> SignUpAction = {
        SignUpAction.None
    },
)
