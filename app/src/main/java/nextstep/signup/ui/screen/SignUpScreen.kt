package nextstep.signup.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.SignUpTextField
import nextstep.signup.ui.component.SignUpTextFieldState
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.ErrorTextColor
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.ui.theme.keyboardEmailNextType
import nextstep.signup.ui.theme.keyboardPasswordNextType
import nextstep.signup.ui.theme.keyboardPasswordSendType

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    SignupTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            var inputedPassword by remember { mutableStateOf("") }

            Spacer(Modifier.height(112.dp))
            SignUpTitle()
            Spacer(Modifier.height(36.dp))
            SignUpTextField(SignUpTextFieldState(label = "Username", supportingText = "")) { userName ->
                updateUserName(userName)
            }
            Spacer(Modifier.height(36.dp))
            SignUpTextField(SignUpTextFieldState(label = "Email", supportingText = ""), keyboardOptions = keyboardEmailNextType) { email ->
                updateEmail(email)
            }
            Spacer(Modifier.height(36.dp))
            SignUpTextField(SignUpTextFieldState(label = "Password", supportingText = ""), keyboardOptions = keyboardPasswordNextType) { password ->
                updatePassword(password).also {
                    inputedPassword = it.text
                }
            }
            Spacer(Modifier.height(36.dp))
            SignUpTextField(SignUpTextFieldState(label = "Password Confirm", supportingText = ""), keyboardOptions = keyboardPasswordSendType) { passwordConfirm ->
                updatePasswordConfirm(passwordConfirm, inputedPassword)
            }
            Spacer(Modifier.height(36.dp))
            SignUpButton("Sign Up")
        }
    }
}

private fun updatePasswordConfirm(
    passwordConfirm: SignUpTextFieldState,
    inputedPassword: String,
) = when {
    passwordConfirm.text.isEmpty() -> passwordConfirm.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )

    passwordConfirm.text != inputedPassword -> passwordConfirm.copy(
        textFieldColors = ErrorTextColor,
        supportingText = "비밀번호가 일치하지 않습니다."
    )

    else -> passwordConfirm.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )
}

private fun updatePassword(password: SignUpTextFieldState) = when {
    password.text.isEmpty() -> password.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )

    password.text.length !in 8..16 -> password.copy(
        textFieldColors = ErrorTextColor,
        supportingText = "비밀번호는 8~16자여야 합니다."
    )

    !password.text.matches(Regex(PASSWORD_REGEX)) -> password.copy(
        textFieldColors = ErrorTextColor,
        supportingText = "비밀번호는 영문과 숫자를 포함해야 합니다."
    )

    else -> password.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )
}

private fun updateEmail(email: SignUpTextFieldState) = when {
    email.text.isEmpty() -> email.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )

    !email.text.matches(Regex(EMAIL_REGEX)) -> email.copy(
        textFieldColors = ErrorTextColor,
        supportingText = "이메일 형식이 올바르지 않습니다."
    )

    else -> email.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )
}

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"


private fun updateUserName(state: SignUpTextFieldState) = when {
    state.text.isEmpty() -> state.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )

    state.text.length !in 2..5 -> state.copy(
        textFieldColors = ErrorTextColor,
        supportingText = "이름은 2~5자여야 합니다."
    )

    !state.text.matches(Regex(USERNAME_REGEX)) -> state.copy(
        textFieldColors = ErrorTextColor,
        supportingText = "이름에는 숫자나 기호가 포함될 수 없습니다."
    )

    else -> state.copy(
        textFieldColors = Blue50,
        supportingText = ""
    )
}

@Composable
private fun SignUpTitle() {
    Text(style = MaterialTheme.typography.titleLarge, text = "Welcome to Compose \uD83D\uDE80")
}