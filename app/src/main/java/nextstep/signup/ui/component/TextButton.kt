package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButtonPreview() {
    TextButton(text = "Preview Button", onClick = {})
}
