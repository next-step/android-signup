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
import nextstep.signup.model.EmailError
import nextstep.signup.model.NameError
import nextstep.signup.model.PasswordConfirmError
import nextstep.signup.model.PasswordError
import nextstep.signup.model.SignUpUserInfo
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpScreen(
    signUpUserInfo: SignUpUserInfo,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
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
                onUsernameChange = onUsernameChange,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onPasswordConfirmChange = onPasswordConfirmChange,
                onClickSignUp = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(context.getString(R.string.complete_signup))
                    }
                },
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 42.dp)
                    .padding(horizontal = 32.dp)
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        // Username TextField
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.username,
            onValueChange = onUsernameChange,
            label = stringResource(id = R.string.username_label),
            isError = signUpUserInfo.nameError != NameError.None &&
                    signUpUserInfo.nameError != NameError.Blank,
            supportingText = when (signUpUserInfo.nameError) {
                NameError.Length -> stringResource(R.string.name_length_error)
                NameError.NumberOrSymbol -> stringResource(R.string.name_number_or_symbol_error)
                NameError.None, NameError.Blank -> null
            }
        )

        // Email TextField
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.email,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.email_label),
            isError = signUpUserInfo.emailError != EmailError.None &&
                    signUpUserInfo.emailError != EmailError.Blank,
            supportingText = when (signUpUserInfo.emailError) {
                EmailError.EmailFormat -> stringResource(R.string.email_format_error)
                EmailError.Blank, EmailError.None -> null
            }
        )

        // Password TextField
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.password_label),
            isError = signUpUserInfo.passwordError != PasswordError.None &&
                    signUpUserInfo.passwordError != PasswordError.Blank,
            supportingText = when (signUpUserInfo.passwordError) {
                PasswordError.PasswordLength -> stringResource(R.string.password_length_error)
                PasswordError.PasswordFormat -> stringResource(R.string.password_format_error)
                PasswordError.Blank, PasswordError.None -> null
            },
            visualTransformation = PasswordVisualTransformation()
        )

        // Password Confirm TextField
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            label = stringResource(id = R.string.password_confirm_label),
            isError = signUpUserInfo.passwordConfirmError != PasswordConfirmError.None &&
                    signUpUserInfo.passwordConfirmError != PasswordConfirmError.Blank,
            supportingText = when (signUpUserInfo.passwordConfirmError) {
                PasswordConfirmError.PasswordEqual -> stringResource(R.string.password_confirm_error)
                PasswordConfirmError.Blank, PasswordConfirmError.None -> null
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = signUpUserInfo.isAllFieldsValid,
            onClick = onClickSignUp,
            contentPadding = PaddingValues(vertical = 15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_button),
                style = MaterialTheme.typography.labelLarge,
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
    var signUpUserInfo by remember {
        mutableStateOf(
            SignUpUserInfo(
                username = "dddddd",
                password = "short"
            )
        )
    }
    SignupTheme {
        SignUpScreen(
            signUpUserInfo = signUpUserInfo,
            onUsernameChange = { signUpUserInfo = signUpUserInfo.copy(username = it) },
            onEmailChange = { signUpUserInfo = signUpUserInfo.copy(email = it) },
            onPasswordChange = { signUpUserInfo = signUpUserInfo.copy(password = it) },
            onPasswordConfirmChange = {
                signUpUserInfo = signUpUserInfo.copy(passwordConfirm = it)
            },
        )
    }
}