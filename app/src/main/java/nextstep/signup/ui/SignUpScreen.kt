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
internal fun SignUpRoute(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    val onUsernameChange = { value: String ->
        username = value
    }
    val onEmailChange = { value: String ->
        email = value
    }
    val onPasswordChange = { value: String ->
        password = value
    }
    val onPasswordConfirmChange = { value: String ->
        passwordConfirm = value
    }

    SignUpScreen(
        username = username,
        email = email,
        password = password,
        passwordConfirm = passwordConfirm,
        onUsernameChange = onUsernameChange,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onPasswordConfirmChange = onPasswordConfirmChange,
        modifier =
            modifier
                .fillMaxSize(),
    )
}

@Composable
internal fun SignUpScreen(
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    usernameValidation: UsernameValidation = remember { UsernameValidation() },
    emailValidation: EmailValidation = remember { EmailValidation() },
    passwordValidation: PasswordValidation = remember { PasswordValidation() },
    passwordConfirmValidation: PasswordConfirmValidation = remember { PasswordConfirmValidation() },
    modifier: Modifier = Modifier,
) {
    val usernameValidationResult by remember(username) {
        derivedStateOf {
            usernameValidation.validate(username)
        }
    }

    val emailValidationResult by remember(email) {
        derivedStateOf {
            emailValidation.validate(email)
        }
    }

    val passwordValidationResult by remember(password) {
        derivedStateOf {
            passwordValidation.validate(password)
        }
    }

    val passwordConfirmValidationResult by remember(
        PasswordConfirmValidation.PasswordConfirm(
            password = password,
            passwordConfirm = passwordConfirm,
        ),
    ) {
        derivedStateOf {
            passwordConfirmValidation.validate(
                PasswordConfirmValidation.PasswordConfirm(
                    password = password,
                    passwordConfirm = passwordConfirm,
                ),
            )
        }
    }

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
            username = username,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
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
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
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
            value = username,
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
            value = email,
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
            value = password,
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
            value = passwordConfirm,
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
    usernameValidationResult.isSuccessFull &&
        emailValidationResult.isSuccessFull &&
        passwordValidationResult.isSuccessFull &&
        passwordConfirmValidationResult.isSuccessFull
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(
    @PreviewParameter(SignInUiStateProvider::class) uiState: SignUpUiState,
) {
    SignupTheme {
        SignUpScreen(
            username = uiState.username,
            email = uiState.email,
            password = uiState.password,
            passwordConfirm = uiState.passwordConfirm,
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordConfirmChange = {},
        )
    }
}

// Preview 용 임시 데이터 클래스
data class SignUpUiState(
    val username: String,
    val email: String,
    val password: String,
    val passwordConfirm: String,
)

class SignInUiStateProvider : PreviewParameterProvider<SignUpUiState> {
    override val values: Sequence<SignUpUiState>
        get() =
            sequenceOf(
                SignUpUiState(
                    username = "user",
                    email = "email@yopmail.com",
                    password = "1q2w3e4r",
                    passwordConfirm = "1q2w3e4r",
                ),
                SignUpUiState(
                    username = "user",
                    email = "",
                    password = "",
                    passwordConfirm = "",
                ),
                SignUpUiState(
                    username = "user",
                    email = "email@yopmail.com",
                    password = "",
                    passwordConfirm = "",
                ),
                SignUpUiState(
                    username = "user",
                    email = "email@yopmail.com",
                    password = "",
                    passwordConfirm = "",
                ),
                SignUpUiState(
                    username = "user",
                    email = "email@yopmail.com",
                    password = "1q2w3e4r",
                    passwordConfirm = "",
                ),
                SignUpUiState(
                    username = "username",
                    email = "email@yopmail.com",
                    password = "1q2w3e4r",
                    passwordConfirm = "1q2w3e4r",
                ),
                SignUpUiState(
                    username = "user",
                    email = "email@yopmail",
                    password = "1q2w3e4r",
                    passwordConfirm = "1q2w3e4r",
                ),
                SignUpUiState(
                    username = "user",
                    email = "email@yopmail.com",
                    password = "1q2",
                    passwordConfirm = "",
                ),
                SignUpUiState(
                    username = "user",
                    email = "email@yopmail.com",
                    password = "1q2w3e4r",
                    passwordConfirm = "1q2w3e4",
                ),
            )
}
