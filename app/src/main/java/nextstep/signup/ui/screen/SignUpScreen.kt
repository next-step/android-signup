package nextstep.signup.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey50
import nextstep.signup.ui.theme.RedError

@Composable
fun SignUpScreen(modifier: Modifier) {
    Surface(
        modifier = modifier.background(Color.White),
    ) {

        var userName by remember { mutableStateOf("") }
        var userErrMsg by remember { mutableStateOf<String?>(null) }

        var email by remember { mutableStateOf("") }
        var emailErrorMsg by remember { mutableStateOf<String?>(null) }

        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleText(
                title = stringResource(R.string.sign_up_title),
                modifier = Modifier.padding(top = 60.dp)
            )
            InputField(
                label = stringResource(R.string.user_name),
                value = userName,
                onValueChange = {
                    userName = it
                    userErrMsg = when {
                        it.length !in 2..5 -> "이름은 2~5자 사이어야 합니다."
                        !it.matches(Regex(RegexPattern.USERNAME_REGEX)) -> "이름은 한글 또는 영문만 입력 가능합니다."
                        else -> null
                    }
                },
                isError = userErrMsg,
                modifier = Modifier.padding(top = 42.dp)
            )
            InputField(
                label = stringResource(R.string.email),
                value = email,
                isError = emailErrorMsg,
                onValueChange = {
                    email = it
                    emailErrorMsg = when {
                        !it.matches(Regex(RegexPattern.EMAIL_REGEX)) -> "이메일 형식이 올바르지 않습니다."
                        else -> null
                    }
                },
                modifier = Modifier.padding(top = 32.dp)
            )
            InputField(
                label = stringResource(R.string.password),
                value = password,
                inputType = KeyboardType.Password,
                onValueChange = { password = it },
                modifier = Modifier.padding(top = 32.dp)
            )
            InputField(
                label = stringResource(R.string.password_confirm),
                value = confirmPassword,
                inputType = KeyboardType.Password,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.padding(top = 32.dp)
            )
            SignUpButton(
                buttonText = stringResource(R.string.sign_up_button),
                modifier = Modifier.padding(top = 42.dp)
            )
        }
    }
}

@Composable
fun TitleText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
        modifier = modifier
    )
}

@Composable
fun InputField(
    label: String,
    value: String,
    isError: String? = null,
    inputType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError != null,
        supportingText = {
            isError?.let {
                Text(
                    text = it,
                    color = RedError,
                    fontSize = 12.sp,
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (inputType == KeyboardType.Password)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        label = {
            Text(
                text = label,
            )
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Blue50,
            unfocusedContainerColor = BlueGrey50,
            focusedContainerColor = BlueGrey50,
            focusedLabelColor = Blue50,
            errorCursorColor = RedError,
            errorLabelColor = RedError,
        ),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun SignUpButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50,
        ),
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 15.dp),
    ) {
        Text(
            text = buttonText,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitlePreView() {
    TitleText(
        title = "회원가입 화면이에용"
    )
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreView() {
    InputField(
        label = "홀리몰리 입력필드",
        value = "홀리몰리",
        onValueChange = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreView() {
    SignUpButton(
        buttonText = "SingUp"
    )
}

object RegexPattern {
    const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
}

