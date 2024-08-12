package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val EMAIL_REX_ERROR = "이메일 형식이 올바르지 않습니다."

const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
    EmailTextFieldView("Username")
}

@Composable
fun EmailTextFieldView(
    label: String = "",
    inputState: MutableState<String> = mutableStateOf("")
) {
    val text = remember { inputState }
    val isFocused = remember { mutableStateOf(false) }
    val supportingText by remember(text) {
        derivedStateOf { getErrorMessage(text.value) }
    }
    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
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
        keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            },
        supportingText = {
            Text(
                text = supportingText, color = Color(0xFFB3261E), fontSize = 12.sp
            )
        })
}

private fun getErrorMessage(
    input: String
): String {
    return if (!input.matches(Regex(EMAIL_REGEX))) {
        EMAIL_REX_ERROR
    } else {
        ""
    }
}