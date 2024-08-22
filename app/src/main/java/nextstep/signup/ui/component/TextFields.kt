package nextstep.signup.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.ErrorTextColor
import nextstep.signup.ui.theme.keyboardTextNextType

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

// usage
//text.matches(Regex(USERNAME_REGEX))

data class SignUpTextFieldState(
    val label: String = "",
    val text: String = "",
    val textFieldColors: Color = Blue50,
    val supportingText: String = "",
    val supportingTextFieldColors: Color = ErrorTextColor,
)

@Composable
@Preview
fun SignUpTextField(
    state: SignUpTextFieldState = SignUpTextFieldState(label = "Username", supportingText = "Hello"),
    keyboardOptions: KeyboardOptions = keyboardTextNextType,
    onTextChanged: ((SignUpTextFieldState) -> SignUpTextFieldState)? = null
) {
    var fieldState by remember { mutableStateOf(state) }

    TextField(
        value = fieldState.text,
        modifier = Modifier.width(296.dp),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = fieldState.textFieldColors,
            focusedIndicatorColor = fieldState.textFieldColors,
        ),
        keyboardOptions = keyboardOptions,
        onValueChange = {
            val new = fieldState.copy(text = it)
            fieldState = onTextChanged?.invoke(new) ?: new
        },
        label = {
            Text(
                text = fieldState.label,
            )
        },
        supportingText = {
            Text(
                text = fieldState.supportingText,
                color = fieldState.supportingTextFieldColors
            )
        },
        visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
    )
}