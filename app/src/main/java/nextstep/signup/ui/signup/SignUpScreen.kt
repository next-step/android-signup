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

            SignUpTitle()
            SignUpInputs(
                username = username,
                email = email,
                password = password,
                passwordConfirm = passwordConfirm,
                onUsernameChange = { username = it },
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onPasswordConfirmChange = { passwordConfirm = it }
            )
            SignUpButton(
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
fun SignUpInputs(
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        UsernameField(
            username = username,
            onUsernameChange = onUsernameChange
        )
        EmailField(
            email = email,
            onEmailChange = onEmailChange
        )
        PasswordField(
            password = password,
            onPasswordChange = onPasswordChange
        )
        PasswordConfirmField(
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChange = onPasswordConfirmChange
        )
    }
}

@Composable
fun SignUpButton(
    onSignUpClick: () -> Unit
) {
    FullButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.sign_up),
        textColor = Color.White,
        containerColor = Color(0xFF2196F3),
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
