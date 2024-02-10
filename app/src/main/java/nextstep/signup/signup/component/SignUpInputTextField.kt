package nextstep.signup.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpInputTextField(
    labelName: String,
    value: String = "",
    modifier: Modifier = Modifier,
    isInputPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (String) -> Unit,
    errorMessage: String? = null,
) {
    Column {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            label = { Text(text = labelName) },
            visualTransformation = when (isInputPassword) {
                true -> PasswordVisualTransformation()
                false -> VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
            onValueChange = onTextChanged,
            isError = errorMessage != null,
        )

        if (errorMessage != null) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                text = errorMessage,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error,
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpInputTextFieldPreview_EmptyInput() {
    SignUpInputTextField(
        labelName = "Label",
        value = "",
        onTextChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpInputTextFieldPreview_FilledInput() {
    SignUpInputTextField(
        labelName = "Label",
        value = "마리빈씨",
        onTextChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpInputTextFieldPreview_PasswordInput() {
    SignUpInputTextField(
        labelName = "Password",
        value = "password",
        isInputPassword = true,
        onTextChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpInputTextFieldPreview_InputWithError() {
    SignUpInputTextField(
        labelName = "Label",
        value = "마리빈씨",
        onTextChanged = {},
        errorMessage = "대충 에러가 발생했다는 텍스트"
    )
}
