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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Black20
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGray20
import nextstep.signup.ui.theme.Gray20
import nextstep.signup.ui.theme.Red
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun SignupTextField(
    label: String,
    text: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    modifier: Modifier = Modifier,
    validator: SignupInfoValidator = SignupInfoValidator.None,
    onValidation: ((Boolean) -> Unit)? = null,
) {
    val validateResult = validator.checkCondition(text)

    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            onValueChange(it)
            onValidation?.invoke(validator.checkCondition(it).isSuccess())
        },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Black20,
            focusedTextColor = Black20,
            unfocusedLabelColor = Gray20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50,
            cursorColor = Blue50,
            focusedContainerColor = BlueGray20,
            unfocusedContainerColor = BlueGray20,
            errorLabelColor = Red,
            errorIndicatorColor = Red
        ),
        supportingText = {
            Text(
                text = validateResult.getErrorMessage(),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp
                )
            )
        },
        isError = validateResult.isError(),
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
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupFieldPreview_userName() {
    var text by remember { mutableStateOf("") }

    SignupTextField(
        label = stringResource(R.string.signup_label_user_name),
        text = text,
        onValueChange = { text = it },
        visualTransformation = VisualTransformation.None,
        validator = SignupInfoValidator.Username
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupFieldPreview_password() {
    var text by remember { mutableStateOf("아무도 안알랴줌") }

    SignupTextField(
        label = stringResource(R.string.signup_label_password),
        text = text,
        onValueChange = { text = it },
        visualTransformation = PasswordVisualTransformation(),
        validator = SignupInfoValidator.Password
    )
}
