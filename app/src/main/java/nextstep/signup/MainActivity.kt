package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue20
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray30
import nextstep.signup.ui.theme.Gray50
import nextstep.signup.ui.theme.Gray70
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        var usernameSupportingText by remember { mutableStateOf("") }
        var emailSupportingText by remember { mutableStateOf("") }
        var passwordSupportingText by remember { mutableStateOf("") }
        var passwordConfirmSupportingText by remember { mutableStateOf("") }

        val validation = SignUpTextFieldValidation()

        val onUsernameChanged: (String) -> Unit = {
            username = it
            usernameSupportingText = validation.getUsernameValidationMessage(username)
        }

        val onEmailChanged: (String) -> Unit = {
            email = it
            emailSupportingText = validation.getEmailValidationMessage(email)
        }

        val onPasswordChanged: (String) -> Unit = {
            password = it
            passwordSupportingText = validation.getPasswordValidationMessage(password, passwordConfirm)
            passwordConfirmSupportingText =
                validation.getPasswordConfirmValidationMessage(password, passwordConfirm)
        }

        val onPasswordConfirmChanged: (String) -> Unit = {
            passwordConfirm = it
            passwordConfirmSupportingText =
                validation.getPasswordConfirmValidationMessage(password, passwordConfirm)
        }

        SignUpTitle(Modifier.padding(top = 60.dp))
        SignUpTextField(
            text = username,
            type = SignUpTextFieldType.USERNAME,
            modifier = Modifier.padding(top = 42.dp),
            onTextChanged = onUsernameChanged,
            errorMessage = usernameSupportingText
        )
        SignUpTextField(
            text = email,
            type = SignUpTextFieldType.EMAIL,
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onEmailChanged,
            errorMessage = emailSupportingText
        )
        SignUpTextField(
            text = password,
            type = SignUpTextFieldType.PASSWORD,
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onPasswordChanged,
            errorMessage = passwordSupportingText
        )
        SignUpTextField(
            text = passwordConfirm,
            type = SignUpTextFieldType.PASSWORD_CONFIRM,
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onPasswordConfirmChanged,
            errorMessage = passwordConfirmSupportingText
        )
        SignUpButton(Modifier.padding(top = 39.dp))
    }
}

@Composable
fun SignUpTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome_to_compose),
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
        color = Color.Black,
        lineHeight = 20.sp,
        modifier = modifier,
    )
}

@Composable
fun SignUpTextField(
    text: String,
    type: SignUpTextFieldType,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit = {},
    errorMessage: String
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        label = {
            Text(
                text = type.hint,
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
            focusedIndicatorColor = Blue50,
            unfocusedIndicatorColor = Gray50,
            cursorColor = Blue50,
            focusedContainerColor = Blue20,
            unfocusedContainerColor = Blue20,
        ),
        supportingText = {
            if (errorMessage.isNotEmpty()) {
                SignUpTextFieldSupportingText(errorMessage)
            }
        },
        visualTransformation = when (type) {
            SignUpTextFieldType.USERNAME, SignUpTextFieldType.EMAIL -> VisualTransformation.None
            SignUpTextFieldType.PASSWORD, SignUpTextFieldType.PASSWORD_CONFIRM -> PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when (type) {
                SignUpTextFieldType.USERNAME -> KeyboardType.Text
                SignUpTextFieldType.EMAIL -> KeyboardType.Email
                SignUpTextFieldType.PASSWORD, SignUpTextFieldType.PASSWORD_CONFIRM -> KeyboardType.Password
            }
        ),
        modifier = modifier
            .width(296.dp)
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
    )
}

@Composable
fun SignUpButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
            .width(296.dp)
            .height(50.dp),
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = Blue50,
            contentColor = Color.White,
            disabledContainerColor = Gray30,
            disabledContentColor = Gray30,
        ),
        content = {
            Text(
                text = stringResource(R.string.sign_up),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp
            )
        }
    )
}

@Composable
fun SignUpTextFieldSupportingText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W400,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle()
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextFieldPreview() {
    var previewText by remember { mutableStateOf("") }
    val onPreviewTextChanged: (String) -> Unit = { previewText = it }

    SignUpTextField(
        text = previewText,
        type = SignUpTextFieldType.USERNAME,
        onTextChanged = onPreviewTextChanged,
        errorMessage = ""
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignUpButton()
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

enum class SignUpTextFieldType(val hint: String) {
    USERNAME("Username"),
    EMAIL("Email"),
    PASSWORD("Password"),
    PASSWORD_CONFIRM("Password Confirm")
}