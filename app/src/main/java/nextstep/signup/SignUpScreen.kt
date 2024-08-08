package nextstep.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

const val USER_NAME_INVALID_CHARACTERS_MESSAGE = "이름에는 숫자나 기호가 포함될 수 없습니다."
const val USER_NAME_LENGTH_VALIDATION_MESSAGE = "이름은 2~5자여야 합니다."
const val EMAIL_INVALID_FORMAT_MESSAGE = "이메일 형식이 올바르지 않습니다."
const val PASSWORD_VALIDATION_MESSAGE = "비밀번호는 8~16자여야 합니다.\n비밀번호는 영문과 숫자를 포함해야 합니다."
const val PASSWORD_MISMATCH_MESSAGE = "비밀번호가 일치하지 않습니다."

@SuppressLint("ResourceAsColor")
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    var userError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordConfirmError by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            text = stringResource(id = R.string.title),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        SignUpInput(
            value = userName,
            onValueChange = {
                userName = it
                userError = validateUserName(userName)
            },
            label = stringResource(id = R.string.userNameLabel),
            errorMessage = userError
        )

        SignUpInput(
            value = email,
            onValueChange = {
                email = it
                emailError = validateEmail(email)
            },
            label = stringResource(id = R.string.emailLabel),
            errorMessage = emailError
        )

        SignUpInput(
            value = password,
            onValueChange = {
                password = it
                passwordError = validatePassword(password)
            },
            visualTransformation = PasswordVisualTransformation(),
            label = stringResource(id = R.string.passwordLabel),
            errorMessage = passwordError
        )

        SignUpInput(
            value = passwordConfirm,
            onValueChange = {
                passwordConfirm = it
                passwordConfirmError = validatePasswordConfirm(passwordConfirm, password)
            },
            visualTransformation = PasswordVisualTransformation(),
            label = stringResource(id = R.string.passwordConfirmLabel),
            errorMessage = passwordConfirmError
        )

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color(R.color.blue),
                disabledContentColor = Color.White
            ),
            content = {
                Text(
                    text = stringResource(id = R.string.signUp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
        )
    }
}

fun validateUserName(value: String): String {
    if (!value.matches(Regex(USERNAME_REGEX))) {
        return USER_NAME_INVALID_CHARACTERS_MESSAGE
    } else if (value.length !in 2..5) {
        return USER_NAME_LENGTH_VALIDATION_MESSAGE
    }
    return ""
}

fun validateEmail(value: String): String {
    if (!value.matches(Regex(EMAIL_REGEX))) {
        return EMAIL_INVALID_FORMAT_MESSAGE
    }
    return ""
}

fun validatePassword(value: String): String {
    if (!value.matches(Regex(PASSWORD_REGEX))) {
        return PASSWORD_VALIDATION_MESSAGE
    }
    return ""
}

fun validatePasswordConfirm(value1: String, value2: String): String {
    if (value1 != value2) {
        return PASSWORD_MISMATCH_MESSAGE
    }
    return ""
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}