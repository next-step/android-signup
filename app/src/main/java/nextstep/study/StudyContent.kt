package nextstep.study

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
@Preview
fun Counter() {
    var number by remember { mutableStateOf(0) }//2.업데이트가 된다.
    Column {
        Text(text = "카운터 앱!")
        Button(onClick = { number++ }) {//1 버튼이 눌린다.
            Text(text = "카운팅: ", fontWeight = FontWeight.Bold)
            Text(text = number.toString())//3. 숫자가 늘어난다
        }
    }
}

//by 키워드를 쓰는 것과 아닌 것의 어떤 적절한 상황들이 있을까??

//composable 은 어떻게 컴파일 되지??
@SuppressLint("UnrememberedMutableState")
@Composable
fun Checker() {
    val checked =  remember { mutableStateOf(false) }
    Column {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = !checked .value}
        )
    }
}

@Composable
fun HelloView(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$name 안녕하세요",
        fontSize = 20.sp,
        color = Color.White,
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Blue, Color.Red),
                    start = Offset.Zero,
                    end = Offset.Infinite
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun HelloViewPreview() {
    HelloView(name = "홍길동")
}