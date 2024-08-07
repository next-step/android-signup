package nextstep.signup.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20

@Composable
fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = SignUpTextFieldDefaults.shape,
    colors: TextFieldColors = SignUpTextFieldDefaults.colors(),
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        shape = shape,
        colors = colors,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        modifier = modifier,
    )
}

@Immutable
object SignUpTextFieldDefaults {
    @Composable
    fun colors(
        focusedContainerColor: Color = BlueGrey20,
        unfocusedContainerColor: Color = BlueGrey20,
        focusedLabelColor: Color = Blue50,
        focusedIndicatorColor: Color = Blue50,
    ): TextFieldColors =
        TextFieldDefaults.colors(
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedLabelColor = focusedLabelColor,
            focusedIndicatorColor = focusedIndicatorColor,
        )

    val shape: Shape
        @Composable get() = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
}

@Preview(showBackground = true)
@Composable
fun SignUpTextFieldPreview() {
    SignUpTextField(
        value = "Hello, World!",
        onValueChange = {},
        label = { },
    )
}
