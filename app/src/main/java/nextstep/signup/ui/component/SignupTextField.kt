package nextstep.signup.ui.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
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
    focusedTextColor: Color = BlackDark,
    unfocusedTextColor: Color = BlackDark,
    focusedContainerColor: Color = BlueGrey20,
    unfocusedContainerColor: Color = BlueGrey20,
    errorContainerColor: Color = BlueGrey20,
    cursorColor: Color = Blue50,
    errorCursorColor: Color = RedDark,
    focusedIndicatorColor: Color = Blue50,
    unfocusedIndicatorColor: Color = BlackGrey,
    errorIndicatorColor: Color = RedDark,
    focusedLabelColor: Color = Blue50,
    unfocusedLabelColor: Color = BlackGrey,
    errorLabelColor: Color = RedDark,
    errorSupportingTextColor: Color = RedDark,
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
            if (isError && errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = errorSupportingTextColor
                )
            }
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
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            errorContainerColor = errorContainerColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            errorIndicatorColor = errorIndicatorColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            errorLabelColor = errorLabelColor,
            errorSupportingTextColor = errorSupportingTextColor
        )
    )
}

class SignupTextFieldPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(false, true)
}

@Preview
@Composable
fun SignupTextFieldPreview(
    @PreviewParameter(SignupTextFieldPreviewProvider::class) isError: Boolean
) {
    SignupTextField(
        label = "Label",
        value = if (isError) "Error" else "Pass",
        onValueChange = { },
        isError = isError,
        errorMessage = if (isError) "error_message" else ""
    )
}