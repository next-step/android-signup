package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(42.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordConfirm by remember { mutableStateOf("") }

            var usernameValid by remember { mutableStateOf(false) }
            var emailValid by remember { mutableStateOf(false) }
            var passwordValid by remember { mutableStateOf(false) }
            var passwordConfirmValid by remember { mutableStateOf(false) }

            SignUpTitle()
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                UsernameField(
                    username = username,
                    onUsernameChange = { username = it },
                    onUsernameValidationSuccess = { usernameValid = it }
                )
                EmailField(
                    email = email,
                    onEmailChange = { email = it },
                    onEmailValidationSuccess = { emailValid = it }
                )
                PasswordField(
                    password = password,
                    onPasswordChange = { password = it },
                    onPasswordValidationSuccess = { passwordValid = it }
                )
                PasswordConfirmField(
                    password = password,
                    passwordConfirm = passwordConfirm,
                    onPasswordConfirmChange = { passwordConfirm = it },
                    onPasswordConfirmValidationSuccess = { passwordConfirmValid = it }
                )
            }
            SignUpButton(
                isEnabled = usernameValid && emailValid && passwordValid && passwordConfirmValid,
                onSignUpClick = { }
            )
        }
    }
}

@Composable
fun SignUpTitle() {
    Text(
        text = stringResource(id = R.string.sign_up_title),
        modifier = Modifier.padding(top = 64.dp),
        color = Color.Black,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.01.em,
        lineHeight = 20.sp,
    )
}

@Composable
fun SignUpButton(
    isEnabled: Boolean,
    onSignUpClick: () -> Unit
) {
    FullButton(
        modifier = Modifier.fillMaxWidth(),
        isEnabled = isEnabled,
        text = stringResource(id = R.string.sign_up),
        textColor = if (isEnabled) Color.White else Color(0xFF1D1B20),
        containerColor = if (isEnabled) Color(0xFF2196F3) else Color(0x1F1D1B20),
        onButtonClick = { onSignUpClick() }
    )
}

@Preview(
    showBackground = true,
    widthDp = 400,
    heightDp = 600
)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle()
}


@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignUpButton(
        isEnabled = true,
        onSignUpClick = { }
    )
}
