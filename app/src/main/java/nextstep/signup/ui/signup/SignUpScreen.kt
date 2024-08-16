package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.DisabledButtonContainerColor
import nextstep.signup.ui.theme.DisabledButtonTextColor

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
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
                    onSignUpClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                context.getString(R.string.sign_up_success)
                            )
                        }
                    }
                )
            }
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
        isEnabled = isEnabled,
        text = stringResource(id = R.string.sign_up),
        textColor = if (isEnabled) Color.White else DisabledButtonTextColor,
        containerColor = if (isEnabled) Blue50 else DisabledButtonContainerColor,
        onButtonClick = { onSignUpClick() },
        modifier = Modifier.fillMaxWidth().testTag("회원가입 버튼")
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
