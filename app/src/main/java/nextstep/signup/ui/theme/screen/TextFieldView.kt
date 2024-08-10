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

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"


@Preview
@Composable
private fun TextFieldPreview() {
    TextFieldView("Username", KeyboardType.Text)
}

@Composable
fun TextFieldView(
    label: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    inputString: MutableState<String> = mutableStateOf("")
) {
    val text = remember { inputString }
    val isFocused = remember { mutableStateOf(false) }
    val supportingText by remember(text, keyboardType) {
        derivedStateOf { getErrorMessage(text.value, keyboardType) }
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
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
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
    keyboardType: KeyboardType
): String {
    return when (keyboardType) {
        KeyboardType.Text -> {
            if (input.length !in 2..5) {
                return "이름은 2~5자여야 합니다."
            } else if (!input.matches(Regex(USERNAME_REGEX))) {
                return "이름에는 숫자나 기호가 포함될 수 없습니다."
            } else {
                ""
            }
        } else -> {
            ""
        }
    }
}