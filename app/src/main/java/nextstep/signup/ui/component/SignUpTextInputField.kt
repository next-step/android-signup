package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

@Composable
fun SignUpTextInputField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val supportingText: @Composable (() -> Unit)? = errorText?.let { { Text(text = it) } }
    TextField(
        modifier = modifier
            .padding(top = 32.dp)
            .fillMaxWidth(),
        value = value,
        label = {
            Text(text = label)
        },
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        supportingText = supportingText,
        isError = !errorText.isNullOrBlank()
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextInputFieldPreview(
    @PreviewParameter(TextInputFieldPreviewParameterProvider::class) textPair: Pair<String, String?>
) {
    SignUpTextInputField(
        value = textPair.first,
        label = "label",
        onValueChange = {},
        errorText = textPair.second
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpPasswordFieldPreview() {
    SignUpTextInputField(
        value = "password",
        label = "label",
        onValueChange = {},
        visualTransformation = PasswordVisualTransformation()
    )
}

internal class TextInputFieldPreviewParameterProvider :
    PreviewParameterProvider<Pair<String, String?>> {
    override val values = sequenceOf(
        "" to null,
        "텍스트 텍스트" to null,
        "긴 텍스트테스트 긴 텍스트테스트 긴 텍스트테스트 긴 텍스트테스트 긴 텍스트테스트  " to null,
        "Text Value" to "Error Message 입니다",
    )
}
