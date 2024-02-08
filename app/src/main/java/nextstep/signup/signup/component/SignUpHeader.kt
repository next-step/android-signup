package nextstep.signup.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SignUpHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Welcome to Compose 🚀",
        modifier = modifier,
        color = Color.Black,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Preview
@Composable
private fun SignUpHeaderPreview() {
    SignUpHeader()
}
