package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.SignupBlue

@Composable
fun SignupForm(
    label: String,
    inputValue: String = "",
    onInputChange: (String) -> Unit = {},
    inputType: KeyboardType = KeyboardType.Text,
) {
    TextField(
        value = inputValue,
        onValueChange = { newText -> onInputChange(newText) },
        label = { Text(text = label) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = SignupBlue,
            unfocusedIndicatorColor = Color.Black,
            focusedLabelColor = SignupBlue,
            unfocusedLabelColor = Color.Black,
            cursorColor = SignupBlue,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = inputType
        ),
        visualTransformation = if (inputType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 32.dp)
    )
}

@Preview
@Composable
fun SignupFormPreview(modifier: Modifier = Modifier) {
    SignupForm(label = "Username")
}