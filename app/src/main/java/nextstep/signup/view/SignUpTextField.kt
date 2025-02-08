package nextstep.signup.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
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
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color(0XFF2196F3),
            focusedIndicatorColor = Color(0XFF2196F3),
            focusedContainerColor = Color(0XFFE3E8F1),
            unfocusedContainerColor = Color(0XFFE3E8F1),
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
private fun SignUpTextField_Username_Preview() {
    SignUpTextField(
        label = "Username",
        value = "김컴포즈",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun SignUpTextField_Email_Preview() {
    SignUpTextField(
        label = "Email",
        value = "test@test.com",
        onValueChange = {},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Preview
@Composable
private fun SignUpTextField_Password_Preview() {
    SignUpTextField(
        label = "Password",
        value = "abcd",
        onValueChange = {},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}