package nextstep.signup.ui.theme

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

val keyboardTextNextType = KeyboardOptions.Default.copy(
    imeAction = ImeAction.Next,
    keyboardType = KeyboardType.Text
)

val keyboardEmailNextType = KeyboardOptions.Default.copy(
    imeAction = ImeAction.Next,
    keyboardType = KeyboardType.Email
)

val keyboardPasswordNextType = KeyboardOptions.Default.copy(
    imeAction = ImeAction.Next,
    keyboardType = KeyboardType.Password
)

val keyboardPasswordSendType = KeyboardOptions.Default.copy(
    imeAction = ImeAction.Send,
    keyboardType = KeyboardType.Password
)