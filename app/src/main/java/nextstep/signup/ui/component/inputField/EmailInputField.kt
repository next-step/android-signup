package nextstep.signup.ui.component.inputField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmailInputField(
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}

@Preview
@Composable
private fun EmailInputFieldPreview() {
    EmailInputField(
        value = "",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun EmailInputFieldPreviewWithLabel() {
    EmailInputField(
        value = "",
        onValueChange = {},
        hint = "Email",
    )
}

@Preview
@Composable
private fun EmailInputFieldPreviewWithValue() {
    EmailInputField(
        value = "abc@nextstep.com",
        onValueChange = {},
    )
}

@Preview
@Composable
private fun EmailInputFieldPreviewWithValueAndLabel() {
    EmailInputField(
        value = "abc@nextstep.com",
        onValueChange = {},
        hint = "Email",
    )
}
