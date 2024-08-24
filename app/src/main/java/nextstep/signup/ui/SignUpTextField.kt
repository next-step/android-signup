package nextstep.signup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpTextFieldComponent(
    labelText: String,
    validText: String,
    validCheck: (String) -> Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var inputText by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text(labelText) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions
        )

        if (validCheck.invoke(inputText)) {
            Text(
                text = validText,
                fontSize = 12.sp,
                color = Color.Red,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpTextFieldComponentPreview() {
    SignUpTextFieldComponent(
        "UserName",
        "이름은 2~5자여아야 합니다.",
        { true }
    )
}
