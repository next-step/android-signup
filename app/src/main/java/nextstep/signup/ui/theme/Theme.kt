package nextstep.signup.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Blue50,
    secondary = BlueGrey20,
    tertiary = Red20,
    background = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue50,
    secondary = BlueGrey20,
    tertiary = Red20,
    background = Color.White,
)

@Composable
fun SignupTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
