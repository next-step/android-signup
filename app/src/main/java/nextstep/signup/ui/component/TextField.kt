package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun TextField(
    label: Int,
    input: MutableState<String>,
    inputEntered: MutableState<Boolean>
) {
    Row(modifier = Modifier.padding(bottom = 32.dp)) {
        val focusManager = LocalFocusManager.current

        TextField(
            value = input.value,
            onValueChange = {
                input.value = it
            },
            label = {
                Text(text = stringResource(id = label))
            },
            modifier = Modifier
                .alignByBaseline()
                .weight(1.0F),
            singleLine = true,
            visualTransformation = setPasswordVisualTransformation(label),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = setKeyboardType(label)
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    inputEntered.value = true
                    if (label == R.string.signup_password_confirm) focusManager.clearFocus()
                    else focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )
    }
}

private fun setPasswordVisualTransformation(label: Int): VisualTransformation {
    return if (label == R.string.signup_password || label == R.string.signup_password_confirm) PasswordVisualTransformation() else VisualTransformation.None
}

private fun setKeyboardType(label: Int): KeyboardType {
    return when (label) {
        R.string.signup_username -> KeyboardType.Text
        R.string.signup_email -> KeyboardType.Email
        R.string.signup_password,
        R.string.signup_password_confirm -> KeyboardType.Password

        else -> KeyboardType.Text
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    val input = remember { mutableStateOf("") }
    val inputEntered = remember { mutableStateOf(false) }

    SignupTheme {
        TextField(
            label = R.string.signup_username,
            input = input,
            inputEntered = inputEntered
        )
    }
}