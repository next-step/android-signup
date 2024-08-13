package nextstep.signup.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGray20
import nextstep.signup.ui.theme.Gray
import nextstep.signup.ui.theme.RobotoRegular

@Composable
fun SignupTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    textFontSize: TextUnit = 16.sp,
    textFontFamily: FontFamily = RobotoRegular,
    label: String,
    labelFontSize: TextUnit = TextUnit.Unspecified,
    labelFontFamily: FontFamily = RobotoRegular,
    focusedTextColor: Color = Color.Black,
    unfocusedTextColor: Color = Color.Black,
    focusedLabelColor: Color = Blue50,
    unfocusedLabelColor: Color = Gray,
    unfocusedContainerColor: Color = BlueGray20,
    focusedContainerColor: Color = BlueGray20,
    focusedIndicatorColor: Color = Blue50,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    supportingText: @Composable (() -> Unit)? = null,
    textFieldBackgroundColor: Color = Color.Transparent,
    textFieldRoundedCornerShape: Shape = RoundedCornerShape(
        topStart = 4.dp,
        topEnd = 4.dp,
    ),
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = TextStyle.Default.copy(
            fontSize = textFontSize,
            fontFamily = textFontFamily,
        ),
        label = {
            Text(
                text = label,
                fontFamily = labelFontFamily,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedContainerColor = focusedContainerColor,
            focusedIndicatorColor = focusedIndicatorColor,
        ),
        isError = isError,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = textFieldBackgroundColor,
                shape = textFieldRoundedCornerShape,
            ),
    )
}

@Preview
@Composable
private fun SignUpTextFieldPreview() {
    SignupTextField(
        label = "비밀번호",
        onTextChanged = {},
        text = "산군",
    )
}