package nextstep.signup.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SignUpInputForm(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = textFieldValue,
        onValueChange = { newTextFieldValue ->
            textFieldValue = newTextFieldValue
        },
        label = { Text(placeHolderText) },
        visualTransformation = visualTransformation,
        singleLine = true,
        placeholder = { Text(placeHolderText) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE3E8F1),
            unfocusedContainerColor = Color(0xFFE3E8F1)
        ),
    )
}


@Preview(showBackground = true)
@Composable
private fun SignUpInputFormPreview() {
    SignUpInputForm(placeHolderText = "placeHolderText", keyboardType = KeyboardType.Text)
}