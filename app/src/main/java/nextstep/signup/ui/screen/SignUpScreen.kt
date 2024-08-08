package nextstep.signup.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UserNameTextField

const val userNameRegex = "^[a-zA-Z가-힣]{2,6}$"
const val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

const val userNameErrorMsg = "이름은 2~5자여야 합니다.\n이름에는 숫자나 기호가 포함될 수 없습니다."
const val emailErrorMsg = "이메일 형식이 올바르지 않습니다."
const val passwordErrorMsg = "비밀번호는 8~16자, 영문, 숫자 조합이어야 합니다.\n비밀번호는 영문과 숫자를 포함해야 합니다."
const val passwordConfirmErrorMsg = "비밀번호가 일치하지 않습니다."

@Composable
fun SignUpScreen() {
    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirm by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 112.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Welcome to Compose \uD83D\uDE80",
            fontSize = 26.sp,
            color = Color.Black,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )

        Column(
            modifier = Modifier.padding(top = 62.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            UserNameTextField(
                userName,
                onTextValueChange = { userName = it }
            )

            EmailTextField(
                text = email,
                onTextValueChange = { email = it }
            )

            PasswordTextField(
                text = password,
                onTextValueChange = { password = it }
            )

            PasswordConfirmTextField(
                text = passwordConfirm,
                onTextValueChange = { passwordConfirm = it },
                password
            )
        }

        Button(modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(), onClick = {}) {
            Text(
                text = "Sign Up",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}