package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Unspecified,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(stringResource(R.string.email)) },
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        singleLine = true,
        isError = (errorMessage == null),
        supportingText = {
            errorMessage?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = Color.Red
                )
            }
        },
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

@Preview
@Composable
private fun EmailTextFieldPreview() {
    SignupTheme {
        EmailTextField(
            value = "email",
            onValueChange = {},
            errorMessage = "Error message",
            imeAction = ImeAction.Done
        )
    }
}