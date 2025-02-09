package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        visualTransformation = visualTransformation,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Cyan,
            focusedLabelColor = Color.Cyan,
            unfocusedIndicatorColor = Color.Black,
        )
    )
}

@Preview
@Composable
private fun MyTextFieldPreview() {
    var input by rememberSaveable { mutableStateOf("") }

    MyTextField(
        value = input ,
        onValueChange = {input = it},
        labelText = "Username"
    )
}