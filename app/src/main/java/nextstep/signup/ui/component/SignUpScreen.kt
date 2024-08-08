package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.username,
            onValueChange = onUsernameChange,
            label = { Text(text = stringResource(id = R.string.username_label)) },
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.email,
            onValueChange = onEmailChange,
            label = { Text(text = stringResource(id = R.string.email_label)) },
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.password,
            onValueChange = onPasswordChange,
            label = { Text(text = stringResource(id = R.string.password_label)) },
            visualTransformation = PasswordVisualTransformation(),
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            label = { Text(text = stringResource(id = R.string.password_confirm_label)) },
            visualTransformation = PasswordVisualTransformation(),
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
    SignUpScreen()
}