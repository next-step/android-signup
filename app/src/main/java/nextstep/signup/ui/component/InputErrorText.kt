package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputErrorText(text: String, errMsg: String, regex: String) {
    if (!text.matches(Regex(regex))) {
        Text(text = errMsg, color = Color.Red)
    }
}

@Preview
@Composable
fun PreviewInputErrorText() {
    InputErrorText("5글자를 넘어가는 아이디", "이름은 2~5자여야 합니다.", "^.{2,5}$")
}