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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.InputErrorText
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UserNameTextField

@Composable
fun SignUpScreen() {
    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirm by rememberSaveable { mutableStateOf("") }

    var userNameValid by rememberSaveable { mutableStateOf(false) }
    var emailValid by rememberSaveable { mutableStateOf(false) }
    var passwordValid by rememberSaveable { mutableStateOf(false) }
    var passwordConfirmValid by rememberSaveable { mutableStateOf(false) }

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
            Column {
                UserNameTextField(
                    userName,
                    onTextValueChange = { userName = it }
                )

                userNameValid =
                    userName.matches(Regex(stringResource(id = R.string.regex_user_name)))

                if (userName.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_user_name),
                        userNameValid
                    )
                }
            }

            Column {
                EmailTextField(
                    text = email,
                    onTextValueChange = { email = it }
                )

                emailValid = email.matches(Regex(stringResource(id = R.string.regex_email)))

                if (email.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_email),
                        emailValid
                    )
                }
            }

            Column {
                PasswordTextField(
                    text = password,
                    onTextValueChange = { password = it }
                )

                passwordValid =
                    password.matches(Regex(stringResource(id = R.string.regex_password)))

                if (password.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_password),
                        passwordValid
                    )
                }
            }

            Column {
                PasswordConfirmTextField(
                    text = passwordConfirm,
                    onTextValueChange = { passwordConfirm = it },
                    password
                )

                passwordConfirmValid = password == passwordConfirm

                if (passwordConfirm.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_password_confirm),
                        passwordConfirmValid
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            onClick = { },
            enabled = userNameValid && emailValid && passwordValid && passwordConfirmValid
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}