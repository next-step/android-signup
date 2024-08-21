package nextstep.signup.ui.component

import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.keyboardTextNextType

@Composable
@Preview
fun SignUpTextField(
    label: String = "Username",
    keyboardOptions: KeyboardOptions = keyboardTextNextType
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        modifier = Modifier.width(296.dp),
        colors = blue50LabelIndicator(),
        keyboardOptions = keyboardOptions,
        onValueChange = { text = it },
        label = {
            Text(
                text = label,
            )
        },
        visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
private fun blue50LabelIndicator() = TextFieldDefaults.colors(
    focusedLabelColor = Blue50,
    focusedIndicatorColor = Blue50,
)