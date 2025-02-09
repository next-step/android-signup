package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    supportingText: String = "",
    isError: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        modifier = modifier.fillMaxWidth().heightIn(min = 56.dp),
        value = value,
        isError = isError,
        onValueChange = onValueChange,
        textStyle = textStyle,
        label = {
            Text(text = label)
        },
        supportingText = {
            if (isError) {
                Text(text = supportingText)
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20,
            errorContainerColor = BlueGrey20,
            cursorColor = Blue50,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
private fun SignUpTextFieldPreview() {
    SignupTheme {
        var text by remember { mutableStateOf("") }
        Column {
            SignUpTextField(
                value = text,
                label = "Username",
                onValueChange = { text = it }
            )
        }
    }
}