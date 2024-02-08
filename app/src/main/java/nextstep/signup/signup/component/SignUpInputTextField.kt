package nextstep.signup.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignUpInputTextField(
    labelName: String,
    value: String = "",
    modifier: Modifier = Modifier,
    isInputPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = { Text(text = labelName) },
        visualTransformation = when (isInputPassword) {
            true -> PasswordVisualTransformation()
            false -> VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        onValueChange = onTextChanged,
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpInputTextFieldPreview_EmptyInput() {
    SignUpInputTextField(
        labelName = "Label",
        value = "",
        onTextChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpInputTextFieldPreview_FilledInput() {
    SignUpInputTextField(
        labelName = "Label",
        value = "마리빈씨",
        onTextChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpInputTextFieldPreview_PasswordInput() {
    SignUpInputTextField(
        labelName = "Password",
        value = "password",
        isInputPassword = true,
        onTextChanged = {},
    )
}
