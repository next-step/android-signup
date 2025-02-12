package nextstep.signup.state

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType

data class InputFieldState(
    @StringRes val label: Int,
    val input: String = "",
    val isError: Boolean = false,
    val supportingText: String = "",
    val keyboardType: KeyboardType = KeyboardType.Text,
    val needHide: Boolean = false,
)
