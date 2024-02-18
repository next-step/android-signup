package nextstep.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.SignUpEnable
import nextstep.signup.ui.component.UserNameTextField

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        var userName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        SignUpTitle()
        Spacer(modifier = Modifier.height(height = 42.dp))
        UserNameTextField(
            userName = userName,
            onValueChange = { value ->
                userName = value
            }
        )
        Spacer(modifier = Modifier.height(height = 36.dp))
        EmailTextField(
            email = email,
            onValueChange = { value ->
                email = value
            }
        )
        Spacer(modifier = Modifier.height(height = 36.dp))
        PasswordTextField(
            password = password,
            onValueChange = { value ->
                password = value
            }
        )
        Spacer(modifier = Modifier.height(height = 36.dp))
        PasswordConfirmTextField(
            password = password,
            passwordConfirm = passwordConfirm,
            onValueChange = { value ->
                passwordConfirm = value
            }
        )
        Spacer(modifier = Modifier.height(height = 42.dp))

        val isEnabled by remember(userName, email, password, passwordConfirm) {
            mutableStateOf(
                SignUpEnable.checkBy(
                    userName = userName,
                    email = email,
                    password = password,
                    confirm = passwordConfirm
                ).isEnabled
            )
        }
        SignUpButton(isEnabled = isEnabled)
    }
}

@Composable
private fun SignUpTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(R.string.welcome_to_compose),
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp,
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle()
}
