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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.signup.SignupValidationResult
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGray20
import nextstep.signup.ui.theme.Gray
import nextstep.signup.ui.theme.RobotoRegular

@Composable
fun SignUpTextField(
    label: String,
    onTextChanged: (String) -> Unit,
    text: String,
    inputValidation: SignupValidationResult = SignupValidationResult.Success,
    visualTransformation: VisualTransformation = VisualTransformation.None,
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
        isError = inputValidation !is Success,
        supportingText = {
            if (inputValidation !is Success) Text(
                text = (inputValidation as Failure).result.message
            )
        },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 4.dp,
                ),
            ),
    )
}

@Preview
@Composable
private fun SignUpTextFieldPreview() {
    SignUpTextField(
        label = "비밀번호",
        onTextChanged = {},
        text = "산군",
    )
}