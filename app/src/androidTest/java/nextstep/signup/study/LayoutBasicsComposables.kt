package nextstep.signup.study

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextView(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        ),
    )
}

@Composable
fun ColumnView() {
    Column(
        modifier = Modifier.testTag("이름")
    ) {
        Text(text = "깜포즈")
        Text(text = "킴포즈")
        Text(text = "끔포즈")
    }
}

@Composable
fun ButtonView() {
    val enabled = remember { mutableStateOf(true) }

    Button(
        onClick = {
            enabled.value = false
        },
        enabled = enabled.value,
        modifier = Modifier.testTag("버튼")
    ) {
        Text(text = "클릭해주세요")
    }
}
