package nextstep.signup.ui.model

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R

enum class SignUpInputType(
    @StringRes
    val labelResId: Int,
    val keyboardType: KeyboardType,
) {
    USERNAME(
        labelResId = R.string.username,
        keyboardType = KeyboardType.Text,
    ),
    EMAIL(
        labelResId = R.string.email,
        keyboardType = KeyboardType.Email,
    ),
    PASSWORD(
        labelResId = R.string.password,
        keyboardType = KeyboardType.Password,
    ),
    PASSWORD_CONFIRM(
        labelResId = R.string.password_confirm,
        keyboardType = KeyboardType.Password,
    );

    fun hasNextField() = entries.last() != this
}