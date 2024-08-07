package nextstep.signup.ui.component.inputField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordInputField(
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview
@Composable
private fun PasswordInputFieldPreview() {
    PasswordInputField(
        value = "",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun PasswordInputFieldPreviewWithLabel() {
    PasswordInputField(
        value = "",
        onValueChange = {},
        hint = "Password",
    )
}

@Preview
@Composable
private fun PasswordInputFieldPreviewWithValue() {
    PasswordInputField(
        value = "1q2w3e4r",
        onValueChange = {},
    )
}

@Preview
@Composable
private fun PasswordInputFieldPreviewWithValueAndLabel() {
    PasswordInputField(
        value = "1q2w3e4r",
        onValueChange = {},
        hint = "Password",
    )
}
