package nextstep.signup.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20

@Composable
fun UserInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imeAction: ImeAction,
) {
    TextField(
        modifier = modifier,
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Color(0xFF49454F),
            focusedIndicatorColor = Blue50,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}