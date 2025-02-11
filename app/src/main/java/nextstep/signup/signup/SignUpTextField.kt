package nextstep.signup.signup

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue20
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray50
import nextstep.signup.ui.theme.Gray70
import nextstep.signup.ui.theme.Red50

@Composable
fun SignUpTextField(
    text: String,
    hintText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit = {},
    errorMessage: String
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        label = {
            Text(
                text = hintText,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.5.sp
            )
        },
        isError = errorMessage.isNotEmpty(),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Gray70,
            unfocusedTextColor = Gray70,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Gray50,
            errorLabelColor = Red50,
            focusedIndicatorColor = Blue50,
            unfocusedIndicatorColor = Gray50,
            errorIndicatorColor = Red50,
            cursorColor = Blue50,
            errorCursorColor = Red50,
            focusedContainerColor = Blue20,
            unfocusedContainerColor = Blue20,
        ),
        supportingText = if (errorMessage.isNotEmpty()) {
            {
                Text(
                    text = errorMessage,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.W400,
                )
            }
        } else {
            null
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .width(296.dp)
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
    )
}
