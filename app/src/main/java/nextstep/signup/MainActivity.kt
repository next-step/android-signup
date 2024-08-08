package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                Scaffold(
                    topBar = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 62.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TitleView("Welcome to Compose \uD83D\uDE80")
                        }
                    },
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextFieldView()
                        }
                    },
                    modifier = Modifier.background(
                        color = Color(0xFFFFFFFF)
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun TextFieldView() {
    val text = remember { mutableStateOf("") }
    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
        }
    )
}

@Preview
@Composable
private fun TitleView(
    title: String = "Welcome to Compose \uD83D\uDE80"
) {
    Text(text = title, fontSize = 26.sp, color = Color(0xFF000000))
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
                    colors = listOf(Color.Red, Color.Green),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                ),
                shape = RoundedCornerShape(16.dp)
            )
    )
}

@Preview(showBackground = true, widthDp = 20)
@Composable
private fun HelloViewPreview() {
    HelloView("윤성현")
}
