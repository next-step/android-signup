package nextstep.signup.ui.theme

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val Blue50 = Color(0xFF2196F3)
val BlueGrey20 = Color(0xFFE3E8F1)

val Error = Color(0xFFB3261E)

val UserTextFiledColor
    @Composable get() = TextFieldDefaults.colors(
        unfocusedContainerColor = BlueGrey20,
        focusedContainerColor = BlueGrey20,
        cursorColor = Blue50,
        focusedLabelColor = Blue50,
        focusedIndicatorColor = Blue50,
        errorContainerColor = BlueGrey20,
        errorCursorColor = Error,
        errorIndicatorColor = Error,
    )