package nextstep.signup.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = BlueDarkTheme,
    secondary = LightBlueDarkTheme,
    tertiary = DarkBlueDarkTheme,
    error = ErrorColor,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = LightBlue,
    tertiary = DarkBlue,
    error = ErrorColor,
)

@Composable
fun SignupTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
