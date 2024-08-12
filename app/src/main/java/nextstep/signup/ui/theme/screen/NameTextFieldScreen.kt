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

const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
const val USERNAME_REX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"

const val USERNAME_MIN_LENGTH = 2
const val USERNAME_MAX_LENGTH = 5


@Preview(showBackground = true)
@Composable
private fun NameTextFieldPreview() {
    NameTextFieldView("Username")
}

@Composable
fun NameTextFieldView(
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
        keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.Text),
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
    return if (input.length !in USERNAME_MIN_LENGTH..USERNAME_MAX_LENGTH) {
        USERNAME_LENGTH_ERROR
    } else if (!input.matches(Regex(USERNAME_REGEX))) {
        USERNAME_REX_ERROR
    } else {
        ""
    }
}