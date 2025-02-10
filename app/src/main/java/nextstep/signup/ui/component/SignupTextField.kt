package nextstep.signup.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = value,
        label = { Text(label) },
        onValueChange = onValueChange,
        maxLines = 1,
        colors = TextFieldDefaults.colors().copy(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20
        ),
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Composable
@Preview
fun SignupTextFieldPreview() {
    val value = remember { mutableStateOf("") }
    SignupTheme {
        SignupTextField(
            value = value.value,
            onValueChange = { value.value = it },
            label = "Username"
        )
    }
}
