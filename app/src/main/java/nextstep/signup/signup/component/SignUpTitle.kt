package nextstep.signup.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 20.sp,
        letterSpacing = 1.sp,
        color = Color.Black
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignupTheme {
        SignUpTitle(title = "Welcome to Compose \uD83D\uDE80")
    }
}