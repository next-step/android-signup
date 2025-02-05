package nextstep.signup.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun SignUpTitlePreview() {
    SignupTheme {
        SignUpTitle(
            text = "Welcome to Compose 🚀"
        )
    }
}
