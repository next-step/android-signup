package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputErrorText(errMsg: String, validResult: Boolean) {
    if (!validResult) {
        Text(text = errMsg, color = Color.Red)
    }
}

@Preview
@Composable
fun PreviewInputErrorText() {
    InputErrorText("이름은 2~5자여야 합니다.", true)
}