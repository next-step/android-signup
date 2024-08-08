package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun SignUpTextField(
    text: String,
    label: String,
    isTextHidden: Boolean = false,
    onTextValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        visualTransformation = if (isTextHidden) PasswordVisualTransformation() else VisualTransformation.None,
        label = { Text(label) },
        value = text,
        maxLines = 1,
        onValueChange = onTextValueChange
    )
}