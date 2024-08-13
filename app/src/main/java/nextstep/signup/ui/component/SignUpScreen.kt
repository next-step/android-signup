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
import nextstep.signup.ui.theme.textfield.NextStepTextField

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
                    if (signUpUserInfo.isAllFieldsValid.not()) {
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
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.username,
            onValueChange = onUsernameChange,
            label = { Text(text = stringResource(id = R.string.username_label)) },
            isError = signUpUserInfo.nameError != NameError.None,
            errorMessage = {
                when (signUpUserInfo.nameError) {
                    NameError.Length -> Text(
                        text = stringResource(R.string.name_length_error),
                        style = MaterialTheme.typography.bodySmall
                    )

                    NameError.NumberOrSymbol -> Text(
                        text = stringResource(R.string.name_number_or_symbol_error),
                        style = MaterialTheme.typography.bodySmall
                    )

                    NameError.None, NameError.Blank -> {}
                }
            }
        )
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.email,
            onValueChange = onEmailChange,
            label = { Text(text = stringResource(id = R.string.email_label)) },
            isError = signUpUserInfo.emailError != EmailError.None,
            errorMessage = {
                when (signUpUserInfo.emailError) {
                    EmailError.EmailFormat -> Text(
                        text = stringResource(R.string.email_format_error),
                        style = MaterialTheme.typography.bodySmall
                    )

                    EmailError.Blank, EmailError.None -> {}
                }
            }
        )
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.password,
            onValueChange = onPasswordChange,
            label = { Text(text = stringResource(id = R.string.password_label)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = signUpUserInfo.passwordError != PasswordError.None,
            errorMessage = {
                when (signUpUserInfo.passwordError) {
                    PasswordError.PasswordLength -> Text(
                        text = stringResource(R.string.password_length_error),
                        style = MaterialTheme.typography.bodySmall
                    )

                    PasswordError.PasswordFormat -> Text(
                        text = stringResource(R.string.password_format_error),
                        style = MaterialTheme.typography.bodySmall
                    )

                    PasswordError.Blank, PasswordError.None -> {}
                }
            }
        )
        NextStepTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpUserInfo.passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            label = { Text(text = stringResource(id = R.string.password_confirm_label)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = signUpUserInfo.passwordConfirmError != PasswordConfirmError.None,
            errorMessage = {
                when (signUpUserInfo.passwordConfirmError) {
                    PasswordConfirmError.PasswordEqual -> {
                        Text(
                            text = stringResource(R.string.password_confirm_error),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    PasswordConfirmError.Blank, PasswordConfirmError.None -> {}
                }
            }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = signUpUserInfo.isNotContainBlank,
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
    var signUpUserInfo by remember { mutableStateOf(SignUpUserInfo(username = "dddddd")) }
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