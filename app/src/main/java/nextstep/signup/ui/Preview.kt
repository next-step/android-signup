package nextstep.signup.ui

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "LightTheme",
    group = "Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "DarkTheme",
    group = "Theme",
    showBackground = true,
    backgroundColor = 0xFF000000,
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class ThemePreviews
