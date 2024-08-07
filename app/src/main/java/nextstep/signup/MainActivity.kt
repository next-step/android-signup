package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Counter()
                        Checker()
                        Checker2()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun HelloView(name: String) {
    Text(
        text = "$name 안녕하세요!",
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Green),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    )
}

@Composable
fun Counter() {
    var number by remember { mutableStateOf(0) }
    Column {
        Text(text = "카운터 앱!")
        Button(onClick = { number++ }) {
            Text(text = "카운팅: ", fontWeight = FontWeight.Bold)
            Text(text = number.toString())
        }
    }
}

@Composable
fun Checker() {
    val checked = remember { mutableStateOf(false) }
    Column {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = !checked.value }
        )
    }
}

@Composable
fun Checker2() {
    var checked by remember { mutableStateOf(false) }
    Column {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = !checked }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HelloViewPreView() {
    SignupTheme {
        Column {
            HelloView(name = "Ironelder")
            Counter()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SignupTheme {
        Column {
            Greeting("Android")
            HelloView(name = "Android")
        }
    }
}
