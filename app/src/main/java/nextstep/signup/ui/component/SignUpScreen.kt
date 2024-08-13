package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.model.SignUpUserInfo
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.ui.theme.textfield.NextStepTextField

@Composable
internal fun SignUpScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var signUpUserInfo by remember { mutableStateOf(SignUpUserInfo()) }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            )
        },
        content = { paddingValues ->
            Content(
                signUpUserInfo = signUpUserInfo,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 42.dp),
                onUsernameChange = { signUpUserInfo = signUpUserInfo.copy(username = it) },
                onEmailChange = { signUpUserInfo = signUpUserInfo.copy(email = it) },
                onPasswordChange = { signUpUserInfo = signUpUserInfo.copy(password = it) },
                onPasswordConfirmChange = {
                    signUpUserInfo = signUpUserInfo.copy(passwordConfirm = it)
                },
                onClickSignUp = {
                    if (signUpUserInfo.isNotContainBlank()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                context.getString(
                                    R.string.sign_up_message,
                                    signUpUserInfo.username,
                                    signUpUserInfo.email,
                                    signUpUserInfo.password
                                )
                            )
                        }
                    }
                }
            )
        },
        bottomBar = { BottomBar() }
    )
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.welcome_message),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                lineHeight = 20.sp,
                letterSpacing = 1.sp,
            )
        )
    }
}

@Composable
private fun Content(
    signUpUserInfo: SignUpUserInfo,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onClickSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.username,
            onValueChange = onUsernameChange,
            label = { Text(text = stringResource(id = R.string.username_label)) },
            isError = signUpUserInfo.isNamePass != SignUpUserInfo.NameError.None,
            errorMessage = {
                when (signUpUserInfo.isNamePass) {
                    SignUpUserInfo.NameError.LengthError -> "이름은 2~5자여야 합니다."
                    SignUpUserInfo.NameError.NumberOrSymbol -> "이름에는 숫자나 기호가 포함될 수 없습니다."
                    else -> ""
                }.also {
                    if (it.isNotBlank()) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        )
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.email,
            onValueChange = onEmailChange,
            label = { Text(text = stringResource(id = R.string.email_label)) },
            isError = signUpUserInfo.isEmailPass != SignUpUserInfo.EmailError.None,
            errorMessage = {
                when (signUpUserInfo.isEmailPass) {
                    SignUpUserInfo.EmailError.EmailFormat -> {
                        Text(
                            text = "이메일 형식이 올바르지 않습니다.",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }

                    else -> {}
                }
            }
        )
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.password,
            onValueChange = onPasswordChange,
            label = { Text(text = stringResource(id = R.string.password_label)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = signUpUserInfo.isPasswordPass != SignUpUserInfo.PasswordError.None,
            errorMessage = {
                when (signUpUserInfo.isPasswordPass) {
                    SignUpUserInfo.PasswordError.PasswordLength -> "비밀번호는 8~16자여야 합니다."
                    SignUpUserInfo.PasswordError.PasswordFormat -> "비밀번호는 영문과 숫자를 포함해야 합니다."
                    else -> ""
                }.also {
                    if (it.isNotBlank()) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        )
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            label = { Text(text = stringResource(id = R.string.password_confirm_label)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = signUpUserInfo.isPasswordConfirmPass != SignUpUserInfo.PasswordConfirmError.None,
            errorMessage = {
                if (signUpUserInfo.isPasswordConfirmPass == SignUpUserInfo.PasswordConfirmError.PasswordEqual) {
                    Text(
                        text = "비밀번호가 일치하지 않습니다.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = signUpUserInfo.isNotContainBlank(),
            onClick = onClickSignUp,
            contentPadding = PaddingValues(vertical = 15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_button),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp
                ),
            )
        }
    }
}

@Composable
private fun BottomBar() {
    // TODO
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}