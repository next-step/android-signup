package nextstep.signup.component.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
fun BaseSignUpTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMessage: String? = null
) {
    val customColors = TextFieldDefaults.colors(
        focusedIndicatorColor = colorResource(id = R.color.blue_50),
        cursorColor = colorResource(id = R.color.blue_50),
        focusedLabelColor = colorResource(id = R.color.blue_50),
        errorIndicatorColor = colorResource(id = R.color.warning_text_color),
        errorLabelColor = colorResource(id = R.color.warning_text_color),
        errorCursorColor = colorResource(id = R.color.warning_text_color)
    )
    TextField(value = text,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = customColors,
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let {
                Text(text = it, color = colorResource(id = R.color.warning_text_color))
            }
        })
}


@Preview
@Composable
private fun BaseSignUpTextFieldPreview() {
    BaseSignUpTextField(text = "compose", onValueChange = {}, label = "Username")
}
