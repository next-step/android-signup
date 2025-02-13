package nextstep.signup.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SubmitButton(
    text: String,
    enabled: Boolean,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        enabled = enabled,
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
private fun SubmitButtonPreview() {
    SubmitButton(
        text = "버튼",
        enabled = true,
        onClickButton = {},
    )
}

@Preview
@Composable
private fun SubmitButtonDisabledPreview() {
    SubmitButton(
        text = "버튼",
        enabled = false,
        onClickButton = {},
    )
}
