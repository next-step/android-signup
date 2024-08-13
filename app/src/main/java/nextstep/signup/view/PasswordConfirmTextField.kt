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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.TextFieldState
import nextstep.signup.TextFieldType
import nextstep.signup.setMessage
import nextstep.signup.view.ui.theme.Red50
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun PasswordConfirmTextField(
    onTextChange: (String) -> Unit = { },
    isMatchPassword: Boolean = true
) {
    var textState by remember { mutableStateOf("") }

    val validationState: TextFieldState = if (isMatchPassword) {
        TextFieldState.Valid
    } else {
        TextFieldState.InValid
    }

    val onValueChange = { value: String ->
        textState = value
        onTextChange(value)
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.password_confirm)) },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = if (validationState == TextFieldState.Valid) {
                MaterialTheme.colorScheme.primary
            } else {
                Red50
            }
        ),
        supportingText = {
            SignUpHelperText(
                setMessage(validationState, TextFieldType.PasswordConfirm)
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPasswordTextFieldPreview() {
    SignupTheme {
        PasswordConfirmTextField()
    }
}