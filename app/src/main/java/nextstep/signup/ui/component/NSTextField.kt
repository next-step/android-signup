package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextFieldCompose(placeholder: String, isTextHidden: Boolean = false) {
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("user textField"),
        visualTransformation = if (isTextHidden) PasswordVisualTransformation() else VisualTransformation.None,
        placeholder = { Text(placeholder) },
        value = text,
        onValueChange = { text = it }
    )
}