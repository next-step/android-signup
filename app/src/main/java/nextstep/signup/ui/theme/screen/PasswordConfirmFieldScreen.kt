package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val PASSWORD_COMPARE_ERROR = "비밀번호가 일치하지 않습니다."

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview() {
    PasswordConfirmTextFieldScreen("Username", "", "") {

    }
}

@Composable
fun PasswordConfirmTextFieldScreen(
    label: String = "",
    inputText: String,
    anotherPassword: String,
    onValueChange: (String) -> Unit = {},
    onValidChanged: (Boolean) -> Unit = {},
) {
    val isFocused = remember { mutableStateOf(false) }
    val supportingText by remember(inputText, anotherPassword) {
        derivedStateOf { getErrorMessage(inputText, anotherPassword) }
    }
    TextField(
        value = inputText,
        onValueChange = {
            onValueChange(it)
            onValidChanged(supportingText.isEmpty())
        },
        label = {
            Text(
                text = label,
                fontSize = if (isFocused.value) 12.sp else 16.sp,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color(0xFF2196F3),
            unfocusedLabelColor = Color(0xFF49454F),
            errorIndicatorColor = Color(0xFFB3261E),
            errorLabelColor = Color(0xFFB3261E),
        ),
        isError = supportingText.isNotEmpty(),
        keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            },
        supportingText = {
            Text(
                text = supportingText,
                color = Color(0xFFB3261E),
                fontSize = 12.sp
            )
        }
    )
}

private fun getErrorMessage(
    input: String,
    anotherInput: String?
): String {
    return return if (input != anotherInput) {
        PASSWORD_COMPARE_ERROR
    } else {
        ""
    }
}