package nextstep.signup.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SignUpTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp,
        fontSize = 26.sp,
        color = Color.Black
    )
}

@Preview
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle("Welcome to Compose 🚀")
}