package nextstep.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Black20
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGray20
import nextstep.signup.ui.theme.Gray20

@Composable
internal fun SignupTextField(
    label: String,
    text: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .testTag(label),
        value = text,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Black20,
            focusedTextColor = Black20,
            unfocusedLabelColor = Gray20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50,
            cursorColor = Blue50,
            focusedContainerColor = BlueGray20,
            unfocusedContainerColor = BlueGray20,
        ),
        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp
                )
            )
        },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupFieldPreview_1() {
    var text by remember { mutableStateOf("") }

    SignupTextField(
        label = "UserName",
        text = text,
        onValueChange = { text = it },
        false
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupFieldPreview_2() {
    var text by remember { mutableStateOf("아무도 안알랴줌") }

    SignupTextField(
        label = "Password",
        text = text,
        onValueChange = { text = it },
        true
    )
}
