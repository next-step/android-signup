package nextstep.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.EmailValidation
import nextstep.signup.ui.component.EmailValidation.EmailValidationResult
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordConfirmValidation
import nextstep.signup.ui.component.PasswordConfirmValidation.PasswordConfirmValidationResult
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.PasswordValidation
import nextstep.signup.ui.component.PasswordValidation.PasswordValidationResult
import nextstep.signup.ui.component.UsernameTextField
import nextstep.signup.ui.component.UsernameValidation
import nextstep.signup.ui.component.UsernameValidation.UsernameValidationResult
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpRoute(
    modifier: Modifier = Modifier,
    usernameValidation: UsernameValidation = remember { UsernameValidation() },
    emailValidation: EmailValidation = remember { EmailValidation() },
    passwordValidation: PasswordValidation = remember { PasswordValidation() },
    passwordConfirmValidation: PasswordConfirmValidation = remember { PasswordConfirmValidation() },
) {
    var uiState by remember {
        mutableStateOf(SignUpUiState.DEFAULT)
    }

    val onUsernameChange = { value: String ->
        uiState = uiState.copy(username = value)
    }
    val onEmailChange = { value: String ->
        uiState = uiState.copy(email = value)
    }
    val onPasswordChange = { value: String ->
        uiState = uiState.copy(password = value)
    }
    val onPasswordConfirmChange = { value: String ->
        uiState = uiState.copy(passwordConfirm = value)
    }

    val usernameValidationResult by remember(uiState.username) {
        derivedStateOf {
            usernameValidation.validate(uiState.username)
        }
    }

    val emailValidationResult by remember(uiState.email) {
        derivedStateOf {
            emailValidation.validate(uiState.email)
        }
    }

    val passwordValidationResult by remember(uiState.password) {
        derivedStateOf {
            passwordValidation.validate(uiState.password)
        }
    }

    val passwordConfirmValidationResult by remember(
        uiState.password,
        uiState.passwordConfirm,
    ) {
        derivedStateOf {
            passwordConfirmValidation.validate(
                PasswordConfirmValidation.PasswordConfirm(
                    password = uiState.password,
                    passwordConfirm = uiState.passwordConfirm,
                ),
            )
        }
    }

    SignUpScreen(
        uiState = uiState,
        onUsernameChange = onUsernameChange,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onPasswordConfirmChange = onPasswordConfirmChange,
        usernameValidationResult = usernameValidationResult,
        emailValidationResult = emailValidationResult,
        passwordValidationResult = passwordValidationResult,
        passwordConfirmValidationResult = passwordConfirmValidationResult,
        modifier =
            modifier
                .fillMaxSize(),
    )
}

@Composable
internal fun SignUpScreen(
    uiState: SignUpUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    usernameValidationResult: UsernameValidationResult,
    emailValidationResult: EmailValidationResult,
    passwordValidationResult: PasswordValidationResult,
    passwordConfirmValidationResult: PasswordConfirmValidationResult,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            val result =
                snackBarHostState.showSnackbar(
                    message = context.getString(R.string.compolete_sign_up_message),
                    duration = SnackbarDuration.Short,
                )
            showSnackbar =
                when (result) {
                    SnackbarResult.Dismissed -> false
                    SnackbarResult.ActionPerformed -> false
                }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.testTag(stringResource(id = R.string.test_tag_snackbar)),
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        SignUpContent(
            uiState = uiState,
            onUsernameChange = onUsernameChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onPasswordConfirmChange = onPasswordConfirmChange,
            usernameValidationResult = usernameValidationResult,
            emailValidationResult = emailValidationResult,
            passwordValidationResult = passwordValidationResult,
            passwordConfirmValidationResult = passwordConfirmValidationResult,
            onSignUpClicked = { showSnackbar = true },
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(32.dp),
        )
    }
}

@Composable
private fun SignUpContent(
    uiState: SignUpUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    usernameValidationResult: UsernameValidationResult,
    emailValidationResult: EmailValidationResult,
    passwordValidationResult: PasswordValidationResult,
    passwordConfirmValidationResult: PasswordConfirmValidationResult,
    modifier: Modifier = Modifier,
    onSignUpClicked: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_title),
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
        )

        UsernameTextField(
            value = uiState.username,
            onValueChange = onUsernameChange,
            keyboardOptions =
                KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
            keyboardActions =
                KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
            validationResult = usernameValidationResult,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .testTag(stringResource(id = R.string.test_tag_username)),
        )

        EmailTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
            keyboardActions =
                KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
            validationResult = emailValidationResult,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .testTag(stringResource(id = R.string.test_tag_email)),
        )

        PasswordTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
            keyboardActions =
                KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
            validationResult = passwordValidationResult,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .testTag(stringResource(id = R.string.test_tag_password)),
        )

        PasswordConfirmTextField(
            value = uiState.passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            validationResult = passwordConfirmValidationResult,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
            keyboardActions =
                KeyboardActions(
                    onDone = { focusManager.clearFocus(true) },
                ),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .testTag(stringResource(id = R.string.test_tag_password_confirm)),
        )

        val isSignUpEnabled =
            isSignUpEnabled(
                usernameValidationResult = usernameValidationResult,
                emailValidationResult = emailValidationResult,
                passwordValidationResult = passwordValidationResult,
                passwordConfirmValidationResult = passwordConfirmValidationResult,
            )

        Button(
            onClick = {
                keyboardController?.hide()
                onSignUpClicked()
            },
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Blue50,
                ),
            enabled = isSignUpEnabled,
            modifier =
                Modifier
                    .padding(top = 42.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag(stringResource(id = R.string.test_tag_btn_sign_up)),
        ) {
            Text(
                text = stringResource(id = R.string.button_sign_up),
            )
        }
    }
}

@Composable
private fun isSignUpEnabled(
    usernameValidationResult: UsernameValidationResult,
    emailValidationResult: EmailValidationResult,
    passwordValidationResult: PasswordValidationResult,
    passwordConfirmValidationResult: PasswordConfirmValidationResult,
) = remember(
    usernameValidationResult,
    emailValidationResult,
    passwordValidationResult,
    passwordConfirmValidationResult,
) {
    usernameValidationResult.isValid &&
        emailValidationResult.isValid &&
        passwordValidationResult.isValid &&
        passwordConfirmValidationResult.isValid
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(
    @PreviewParameter(SignInUiStateProvider::class) param: SignUpScreenPreviewParameter,
) {
    SignupTheme {
        SignUpScreen(
            uiState = param.uiState,
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordConfirmChange = {},
            usernameValidationResult = param.usernameValidationResult,
            emailValidationResult = param.emailValidationResult,
            passwordValidationResult = param.passwordValidationResult,
            passwordConfirmValidationResult = param.passwordConfirmValidationResult,
        )
    }
}

data class SignUpScreenPreviewParameter(
    val uiState: SignUpUiState,
    val usernameValidationResult: UsernameValidationResult,
    val emailValidationResult: EmailValidationResult,
    val passwordValidationResult: PasswordValidationResult,
    val passwordConfirmValidationResult: PasswordConfirmValidationResult,
)

class SignInUiStateProvider : PreviewParameterProvider<SignUpScreenPreviewParameter> {
    override val values: Sequence<SignUpScreenPreviewParameter>
        get() =
            sequenceOf(
                SignUpScreenPreviewParameter(
                    uiState = SignUpUiState.DEFAULT,
                    usernameValidationResult = UsernameValidationResult.Empty,
                    emailValidationResult = EmailValidationResult.Empty,
                    passwordValidationResult = PasswordValidationResult.Empty,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.Empty,
                ),
                SignUpScreenPreviewParameter(
                    uiState =
                        SignUpUiState(
                            username = "user",
                            email = "email@yopmail.com",
                            password = "1q2w3e4r",
                            passwordConfirm = "1q2w3e4r",
                        ),
                    usernameValidationResult = UsernameValidationResult.Success,
                    emailValidationResult = EmailValidationResult.Success,
                    passwordValidationResult = PasswordValidationResult.Success,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.Success,
                ),
                SignUpScreenPreviewParameter(
                    uiState =
                        SignUpUiState(
                            username = "user",
                            email = "",
                            password = "",
                            passwordConfirm = "",
                        ),
                    usernameValidationResult = UsernameValidationResult.Success,
                    emailValidationResult = EmailValidationResult.Empty,
                    passwordValidationResult = PasswordValidationResult.Empty,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.Empty,
                ),
                SignUpScreenPreviewParameter(
                    uiState =
                        SignUpUiState(
                            username = "user",
                            email = "email@yopmail.com",
                            password = "",
                            passwordConfirm = "",
                        ),
                    usernameValidationResult = UsernameValidationResult.Success,
                    emailValidationResult = EmailValidationResult.Success,
                    passwordValidationResult = PasswordValidationResult.Empty,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.Empty,
                ),
                SignUpScreenPreviewParameter(
                    uiState =
                        SignUpUiState(
                            username = "username",
                            email = "email@yopmail.com",
                            password = "1q2w3e4r",
                            passwordConfirm = "1q2w3e4r",
                        ),
                    usernameValidationResult = UsernameValidationResult.UsernameLengthError,
                    emailValidationResult = EmailValidationResult.Success,
                    passwordValidationResult = PasswordValidationResult.Success,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.Success,
                ),
                SignUpScreenPreviewParameter(
                    uiState =
                        SignUpUiState(
                            username = "user",
                            email = "email@yopmail",
                            password = "1q2w3e4r",
                            passwordConfirm = "1q2w3e4r",
                        ),
                    usernameValidationResult = UsernameValidationResult.Success,
                    emailValidationResult = EmailValidationResult.EmailFormatError,
                    passwordValidationResult = PasswordValidationResult.Success,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.Success,
                ),
                SignUpScreenPreviewParameter(
                    uiState =
                        SignUpUiState(
                            username = "user",
                            email = "email@yopmail.com",
                            password = "1q2",
                            passwordConfirm = "",
                        ),
                    usernameValidationResult = UsernameValidationResult.Success,
                    emailValidationResult = EmailValidationResult.Success,
                    passwordValidationResult = PasswordValidationResult.PasswordLengthError,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.Empty,
                ),
                SignUpScreenPreviewParameter(
                    uiState =
                        SignUpUiState(
                            username = "user",
                            email = "email@yopmail.com",
                            password = "1q2w3e4r",
                            passwordConfirm = "1q2w3e4",
                        ),
                    usernameValidationResult = UsernameValidationResult.Success,
                    emailValidationResult = EmailValidationResult.Success,
                    passwordValidationResult = PasswordValidationResult.Success,
                    passwordConfirmValidationResult = PasswordConfirmValidationResult.PasswordNotMatchError,
                ),
            )
}
