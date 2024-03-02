package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun TextInputField(
    modifier: Modifier = Modifier, value: String, label: String, onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = label)
        },
        onValueChange = onValueChange
    )
}

@Preview(showBackground = true)
@Composable
private fun TextInputFieldPreview(
    @PreviewParameter(TextInputFieldPreviewParameterProvider::class) text: String
) {
    TextInputField(
        value = text,
        label = "label",
        onValueChange = {}
    )
}

internal class TextInputFieldPreviewParameterProvider :
    PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "",
        "TextValue",
        "긴 텍스트테스트 긴 텍스트테스트 긴 텍스트테스트 긴 텍스트테스트 긴 텍스트테스트  "
    )
}


