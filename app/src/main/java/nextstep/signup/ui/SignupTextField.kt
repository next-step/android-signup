package nextstep.signup.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.BlackDark
import nextstep.signup.ui.theme.BlackGrey
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.RedDark


@Composable
fun SignupTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    errorMessage: String = "",
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall
            )
        },
        singleLine = true,
        isError = isError,
        supportingText = {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall
            )
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = RoundedCornerShape(
            topStart = 4.dp,
            topEnd = 4.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = BlackDark,
            unfocusedTextColor = BlackDark,
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20,
            errorContainerColor = BlueGrey20,
            cursorColor = Blue50,
            errorCursorColor = RedDark,
            focusedIndicatorColor = Blue50,
            unfocusedIndicatorColor = BlackGrey,
            errorIndicatorColor = RedDark,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = BlackGrey,
            errorLabelColor = RedDark,
            errorSupportingTextColor = RedDark
        )
    )
}

@Preview
@Composable
fun SignupTextFieldValidPreview() {
    SignupTextField(
        label = "Email",
        value = "abc@gmail.com",
        onValueChange = { },
    )
}

@Preview
@Composable
fun SignupTextFieldErrorPreview() {
    SignupTextField(
        label = "Email",
        value = "abc#gmail.com",
        onValueChange = { },
        isError = true,
        errorMessage = "이메일 형식이 올바르지 않습니다."
    )
}