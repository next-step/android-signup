package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            SignUpTitle(modifier = Modifier.padding(top = 64.dp))
        }
    }
}

@Composable
fun SignUpTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Welcome to Compose \uD83D\uDE80",
        modifier = modifier,
        color = Color.Black,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.01.em,
        lineHeight = 20.sp,
    )
}

@Preview(
    showBackground = true,
    widthDp = 400,
    heightDp = 500
)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle()
}
