package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextInputField(
    modifier: Modifier = Modifier, value: String, label: String, onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = label)
        },
        onValueChange = onValueChange
    )
}

@Preview(showBackground = true)
@Composable
fun TextInputFieldEmptyPreview() {
    TextInputField(
        value = "",
        label = "label",
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TextInputFieldPreview() {
    TextInputField(
        value = "TextValue",
        label = "label",
        onValueChange = {}
    )
}
