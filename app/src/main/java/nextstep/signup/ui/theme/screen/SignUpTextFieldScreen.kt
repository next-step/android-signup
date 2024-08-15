package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.screen.validator.NameValidator
import nextstep.signup.ui.theme.screen.validator.TextFieldValidator

@Preview
@Composable
private fun SignUpTextFieldPreView() {
    SignUpTextField(
        inputText = "",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        visualTransformation = VisualTransformation.None,
        validator = NameValidator()
    )
}

@Composable
fun SignUpTextField(
    label: String = "",
    inputText: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    validator: TextFieldValidator,
    onValueChange: (String) -> Unit = {},
    onValidChanged: (Boolean) -> Unit = {},
) {
    val isFocused = remember { mutableStateOf(false) }
    var supportingText = validator.getErrorMessage(inputText)
    TextField(
        value = inputText,
        onValueChange = {
            onValueChange(it)
            supportingText = validator.getErrorMessage(it)
            onValidChanged(supportingText.isNotEmpty())
        },
        label = {
            Text(
                text = label,
                fontSize = if (isFocused.value) 12.sp else 16.sp,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color(0xFF2196F3),
            unfocusedLabelColor = Color(0xFF49454F),
            errorIndicatorColor = Color(0xFFB3261E),
            errorLabelColor = Color(0xFFB3261E),
        ),
        isError = supportingText.isNotEmpty(),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            },
        supportingText = {
            Text(
                text = supportingText, color = Color(0xFFB3261E), fontSize = 12.sp
            )
        })
}