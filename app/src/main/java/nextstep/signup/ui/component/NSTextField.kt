package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignUpTextField(
    inputText: String,
    label: String,
    onTextValueChange: (String) -> Unit,
    validResult: Boolean,
    visualTransformation: VisualTransformation,
    errMsg: String
) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            value = inputText,
            visualTransformation = visualTransformation,
            maxLines = 1,
            onValueChange = onTextValueChange,
        )

        if (!validResult) {
            InputErrorText(errMsg)
        }
    }
}

@Preview
@Composable
private fun TestTextFieldPrev() {
    SignUpTextField(
        inputText = "김컴포즈",
        label = "이름",
        onTextValueChange = {},
        validResult = true,
        visualTransformation = VisualTransformation.None,
        errMsg = "이름은 2~5자여야 합니다."
    )
}

