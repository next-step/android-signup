package nextstep.signup.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.BlackDark
import nextstep.signup.ui.theme.BlackGrey
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20


@Composable
fun SignupTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(
            color = BlackDark,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400
        ),
        label = {
            Text(text = label)
        },
        singleLine = true,
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
            cursorColor = Blue50,
            focusedIndicatorColor = Blue50,
            unfocusedIndicatorColor = BlackGrey,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = BlackGrey,
        )
    )
}

@Preview
@Composable
fun SignupTextFieldPreview() {
    SignupTextField(
        label = "Email",
        value = "abc@gmail.com",
        onValueChange = { },
    )
}