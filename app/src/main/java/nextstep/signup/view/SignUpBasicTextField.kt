package nextstep.signup.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SignUpBasicTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMessage: String = "",
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = Color(0XFF1D1B20),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp
        ),
        isError = errorMessage.isNotEmpty(),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color(0XFF2196F3),
            focusedIndicatorColor = Color(0XFF2196F3),
            focusedContainerColor = Color(0XFFE3E8F1),
            unfocusedContainerColor = Color(0XFFE3E8F1),
            errorTextColor = Color(0XFFBD413A),
            errorIndicatorColor = Color(0XFFBD413A),
            errorLabelColor = Color(0XFFBD413A)
        ),
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        },
        supportingText = {
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                )
            }
        },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions
    )
}

@Preview(name = "Username 입력 TextField")
@Composable
private fun SignUpTextFieldPreview1() {
    SignUpBasicTextField(
        label = "Username",
        value = "김컴포즈",
        onValueChange = {}
    )
}

@Preview(name = "Email 입력 TextField")
@Composable
private fun SignUpTextFieldPreview2() {
    SignUpBasicTextField(
        label = "Email",
        value = "test@test.com",
        onValueChange = {},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Preview(name = "Password 입력 TextField")
@Composable
private fun SignUpTextFieldPreview3() {
    SignUpBasicTextField(
        label = "Password",
        value = "abcd",
        onValueChange = {},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}