package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray10
import nextstep.signup.ui.theme.Gray40
import nextstep.signup.ui.theme.Red40
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpTextField(
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    isError: Boolean = false,
    supportingText: @Composable () -> Unit = {},
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedLabelColor = Blue50,
        focusedIndicatorColor = Blue50,
        unfocusedLabelColor = Gray40,
        cursorColor = Blue50,
        focusedTextColor = Gray10,
        unfocusedTextColor = Gray10,
        errorTextColor = Red40
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        label = {
            Text(
                text = labelText
            )
        },
        supportingText = supportingText,
        colors = colors,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        isError = isError,
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextFieldPreview() {
    SignupTheme {
        SignUpTextField(
            text = "",
            labelText = "Preview",
            onValueChange = {}
        )
    }
}


