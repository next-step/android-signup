package nextstep.signup.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.model.InputValidator.isValid
import nextstep.signup.R
import nextstep.signup.model.TextFieldState
import nextstep.signup.model.TextFieldType
import nextstep.signup.model.setMessage
import nextstep.signup.view.ui.theme.Red50
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun UsernameTextField() {
    var textState by remember { mutableStateOf("") }
    var validationState: TextFieldState by remember { mutableStateOf(TextFieldState.Default) }

    val onValueChange = { value: String ->
        textState = value
        validationState = isValid(value, TextFieldType.Username)
    }

    UsernameContent(textState, onValueChange, validationState)
}

@Composable
fun UsernameContent(
    textState: String,
    onValueChange: (String) -> Unit,
    validationState: TextFieldState
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.username)) },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = if (validationState == TextFieldState.Valid || validationState == TextFieldState.Default) {
                MaterialTheme.colorScheme.primary
            } else {
                Red50
            }
        ),
        supportingText = {
            SignUpHelperText(
                setMessage(validationState, TextFieldType.Username)
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun UsernameTextFieldPreview() {
    SignupTheme {
        UsernameTextField()
    }
}