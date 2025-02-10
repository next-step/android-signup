package nextstep.signup.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    secure: Boolean = false,
) {
    TextField(
        modifier = modifier,
        value = value,
        label = { Text(label) },
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.colors().copy(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
        visualTransformation = if (secure) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = keyboardOptions,
    )
}

@Composable
@Preview
fun SignupTextFieldPreview() {
    val value = remember { mutableStateOf("") }
    SignupTheme {
        SignupTextField(
            value = value.value,
            onValueChange = { value.value = it },
            label = "Username",
            secure = false,
        )
    }
}

@Composable
@Preview
fun SignupSecureTextFieldPreview() {
    val value = remember { mutableStateOf("") }
    SignupTheme {
        SignupTextField(
            value = value.value,
            onValueChange = { value.value = it },
            label = "Username",
            secure = true,
        )
    }
}
