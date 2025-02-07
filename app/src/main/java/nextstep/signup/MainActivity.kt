package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun SignUpTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
        color = Color.Black,
        lineHeight = 20.sp,
        modifier = modifier,
    )
}


@Preview(showBackground = true)
@Composable
private fun TitleComposablePreview() {
    SignUpTitle("Welcome to Compose \uD83D\uDE80")
}