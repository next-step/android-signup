package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {

            }
        }
    }
}

@Composable
fun HelloView(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$name 안녕하세요!",
        fontSize = 20.sp,
        color = Color.White,
        modifier = modifier
            .background(
                brush = Brush.linearGradient(

                )
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun HelloViewPreview(){
    HelloView(name = "정석준")
}