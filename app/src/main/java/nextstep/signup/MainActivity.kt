package nextstep.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                Counter()
            }
        }
    }
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun Checker() {
    val checked = remember{
        mutableStateOf(false)
    }
    Column {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = !checked.value }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HelloViewPreview() {
    Counter()
    Checker()
}