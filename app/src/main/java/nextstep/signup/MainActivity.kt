package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                TitleTextComponent()
            }
        }
    }
}

@Composable
private fun TitleTextComponent() {
    Text(
        text = "Welcome to Compose \uD83D\uDE80",
        style = TextStyle(
            fontSize = 26.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(700),
            color = Color.Black,
            letterSpacing = 0.26.sp
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TitleTextComponentPreview() {
    SignupTheme {
        TitleTextComponent()
    }
}
