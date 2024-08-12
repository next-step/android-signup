package nextstep.signup.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputView(hint: String) {
    var inputValue by remember { mutableStateOf("") }
    TextField(modifier = Modifier
        .width(296.dp)
        .height(53.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFF2196F3),
            focusedLabelColor = Color(0xFF2196F3),
            unfocusedIndicatorColor = Color(0xFF49454F),
            unfocusedLabelColor = Color(0xFF49454F),
        ),
        shape = RoundedCornerShape(4.dp, 4.dp, 0.dp, 0.dp),
        value = inputValue,
        onValueChange = {
            inputValue = it
        },
        label = {
            Text(hint)
        })
}

@Preview
@Composable
fun PreviewInputView() {
    InputView("이름을 입력해주세요")
}