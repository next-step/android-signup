package nextstep.signup.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    needHide: Boolean = false,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (needHide) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20,
        )
    )
}

@Preview
@Composable
private fun SignUpTextFieldPreview() {
    var text by remember { mutableStateOf("김컴포즈") }

    SignupTheme {
        SignUpTextField(
            value = text,
            onValueChange = { text = it },
            label = "Username",
        )
    }
}

@Preview
@Composable
private fun SignUpTextFieldWithPasswordPreview() {
    var text by remember { mutableStateOf("password1234") }

    SignupTheme {
        SignUpTextField(
            value = text,
            onValueChange = { text = it },
            needHide = true,
            label = "Password",
        )
    }
}
