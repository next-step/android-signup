package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Preview
@Composable
fun PasswordInputFieldEmptyPreview() {
    PasswordInputField(
        value = "",
        label = "label",
        onValueChange = {}
    )
}

@Preview
@Composable
fun PasswordInputFieldPreview() {
    PasswordInputField(
        value = "TextValue",
        label = "label",
        onValueChange = {}
    )
}
