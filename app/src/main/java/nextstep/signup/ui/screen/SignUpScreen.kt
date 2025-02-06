package nextstep.signup.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignUpScreen(modifier: Modifier) {
    Surface(
        modifier = modifier.background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(top = 60.dp))
            TitleComponent("Welcome to Compose \uD83D\uDE80")
        }
    }
}

@Composable
fun TitleComponent(title: String) {
    Text(
        text = title,
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
    )
}

@Preview(showBackground = true)
@Composable
private fun TitlePreView() {
    TitleComponent("Welcome to Compose \uD83D\uDE80")
}