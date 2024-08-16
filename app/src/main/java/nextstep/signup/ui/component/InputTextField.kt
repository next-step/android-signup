package nextstep.signup.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .background(BlueGrey20),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
            cursorColor = Blue50
        )
    )
}
