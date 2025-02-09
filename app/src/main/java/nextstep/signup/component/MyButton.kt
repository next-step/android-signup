package nextstep.signup.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyButton(
    text: String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClickButton,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
        )
    ) {
        Text(text)
    }
}

@Preview
@Composable
private fun MyButtonPreview() {
    MyButton(
        text = "버튼",
        onClickButton = {},
    )
}