package nextstep.signup.ui.common.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGray20
import nextstep.signup.ui.theme.Gray
import nextstep.signup.ui.theme.RobotoRegular

@Composable
fun SignupTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    componentDescription: String = stringResource(R.string.signup_default_description),
    supportingText: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontFamily = RobotoRegular,
        ),
        label = {
            Text(
                text = label,
                fontFamily = RobotoRegular,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Gray,
            unfocusedContainerColor = BlueGray20,
            focusedContainerColor = BlueGray20,
            focusedIndicatorColor = Blue50,
        ),
        isError = isError,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 4.dp,
                ),
            )
            .semantics { contentDescription = componentDescription },
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