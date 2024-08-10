package nextstep.signup.ui.component

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.Black10
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.Grey10
import nextstep.signup.ui.theme.Red10

@Composable
fun NSTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    supportingTextValue: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelValue) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        supportingText = supportingTextValue?.let { { Text(text = it) } },
        isError = supportingTextValue != null,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Black10,
            unfocusedTextColor = Black10,
            errorTextColor = Black10,
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20,
            errorContainerColor = BlueGrey20,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Grey10,
            errorLabelColor = Red10,
            focusedIndicatorColor = Blue50,
            unfocusedIndicatorColor = Grey10,
            errorIndicatorColor = Red10,
            errorSupportingTextColor = Red10,
        ),
        modifier = modifier.testTag("nsTextField"),
    )
}

@Preview
@Composable
private fun NSTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    NSTextField(value = value, onValueChange = { value = it }, labelValue = "label")
}

@Preview
@Composable
private fun NSTextFieldErrorCasePreview() {
    var value by remember { mutableStateOf("abc") }

    NSTextField(
        value = value,
        onValueChange = { value = it },
        labelValue = "label",
        supportingTextValue = "supporting text"
    )
}
