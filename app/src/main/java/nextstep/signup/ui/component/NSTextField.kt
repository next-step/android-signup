package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.ui.screen.emailRegex
import nextstep.signup.ui.screen.passwordRegex
import nextstep.signup.ui.screen.userNameRegex

@Composable
fun SignUpTextField(
    text: String,
    label: String,
    isTextHidden: Boolean = false,
    onTextValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        visualTransformation = if (isTextHidden) PasswordVisualTransformation() else VisualTransformation.None,
        label = { Text(label) },
        value = text,
        maxLines = 1,
        onValueChange = onTextValueChange
    )
}

@Composable
fun UserNameTextField(text: String, onTextValueChange: (String) -> Unit) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("UserName") },
            value = text,
            maxLines = 1,
            onValueChange = onTextValueChange
        )

        InputErrorText(
            userNameErrorMsg,
            !text.matches(Regex(userNameRegex)) && text.isNotEmpty()
        )
    }
}

@Composable
fun EmailTextField(text: String, onTextValueChange: (String) -> Unit) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("Email") },
            value = text,
            maxLines = 1,
            onValueChange = onTextValueChange
        )

        InputErrorText(
            emailErrorMsg,
            !text.matches(Regex(emailRegex)) && text.isNotEmpty()
        )
    }
}

@Composable
fun PasswordTextField(text: String, onTextValueChange: (String) -> Unit) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("Password") },
            value = text,
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            onValueChange = onTextValueChange
        )
        InputErrorText(
            passwordErrorMsg,
            !text.matches(Regex(passwordRegex)) && text.isNotEmpty()
        )
    }
}

@Composable
fun PasswordConfirmTextField(text: String, onTextValueChange: (String) -> Unit, password: String) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("PasswordConfirm") },
            value = text,
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            onValueChange = onTextValueChange
        )
        InputErrorText(
            passwordConfirmErrorMsg,
            password != text && text.isNotEmpty()
        )
    }
}

private const val userNameErrorMsg = "이름은 2~5자여야 합니다.\n이름에는 숫자나 기호가 포함될 수 없습니다."
private const val emailErrorMsg = "이메일 형식이 올바르지 않습니다."
private const val passwordErrorMsg = "비밀번호는 8~16자, 영문, 숫자 조합이어야 합니다.\n비밀번호는 영문과 숫자를 포함해야 합니다."
private const val passwordConfirmErrorMsg = "비밀번호가 일치하지 않습니다."