package nextstep.signup.ui.component.inputField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
) {
    TextField(
        modifier = modifier
            .then(Modifier.fillMaxWidth()),
        value = value,
        onValueChange = onValueChange,
        label = { Text(hint) },
        singleLine = true,
    )
}

@Preview
@Composable
private fun TextInputFieldPreview() {
    TextInputField(
        value = "",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun TextInputFieldPreviewWithLabel() {
    TextInputField(
        value = "",
        onValueChange = {},
        hint = "Username",
    )
}

@Preview
@Composable
private fun TextInputFieldPreviewWithValue() {
    TextInputField(
        value = "username",
        onValueChange = {},
    )
}

@Preview
@Composable
private fun TextInputFieldPreviewWithValueAndLabel() {
    TextInputField(
        value = "username",
        onValueChange = {},
        hint = "Username",
    )
}
