package nextstep.signup.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.component.SignupButton
import nextstep.signup.ui.component.SignupSnackbar
import nextstep.signup.ui.signin.EmailTextField
import nextstep.signup.ui.signin.PasswordTextField
import nextstep.signup.ui.signin.SignupValidator
import nextstep.signup.ui.signin.UserNameTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupScreen() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val hostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Input State
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    // Validation State
    val usernameValidation by remember {
        derivedStateOf { SignupValidator.validateUsername(username) }
    }
    val emailValidation by remember {
        derivedStateOf { SignupValidator.validateEmail(email) }
    }
    val passwordValidation by remember {
        derivedStateOf { SignupValidator.validatePassword(password) }
    }
    val passwordConfirmValidation by remember {
        derivedStateOf { SignupValidator.validatePassword(passwordConfirm) }
    }
    val isPasswordMatchValidation by remember {
        derivedStateOf { SignupValidator.validatePasswordMatch(password, passwordConfirm) }
    }
    val isSignupButtonEnable by remember {
        derivedStateOf {
            usernameValidation == SignupValidator.ResultType.Success &&
                    emailValidation == SignupValidator.ResultType.Success &&
                    passwordValidation == SignupValidator.ResultType.Success &&
                    passwordConfirmValidation == SignupValidator.ResultType.Success
        }
    }
    val snackbarMessage = when (isPasswordMatchValidation) {
        SignupValidator.ResultType.Success -> stringResource(R.string.signup_complete)
        else -> stringResource(R.string.signup_error_password_mismatch)
    }

    Scaffold(
        snackbarHost = { SignupSnackbar(hostState = hostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 33.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Title(
                text = stringResource(R.string.signup_title),
                modifier = Modifier.padding(top = 60.dp)
            )

            UserNameTextField(
                label = stringResource(R.string.signup_label_username),
                value = username,
                onValueChange = { username = it },
                validationResult = usernameValidation,
                modifier = Modifier.padding(top = 23.dp),
                imeAction = ImeAction.Next,
            )

            EmailTextField(
                label = stringResource(R.string.signup_label_email),
                value = email,
                onValueChange = { email = it },
                validationResult = emailValidation,
                imeAction = ImeAction.Next,
            )

            PasswordTextField(
                label = stringResource(R.string.signup_label_password),
                value = password,
                onValueChange = { password = it },
                validationResult = passwordValidation,
                imeAction = ImeAction.Next
            )

            PasswordTextField(
                label = stringResource(R.string.signup_label_password_confirm),
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                validationResult = passwordConfirmValidation,
                imeAction = ImeAction.Done
            )

            SignupButton(
                text = stringResource(R.string.signup_button),
                onClick = {
                    keyboardController?.hide()
                    coroutineScope.launch {
                        hostState.showSnackbar(message = snackbarMessage)
                    }
                },
                modifier = Modifier.padding(top = 3.dp),
                enabled = isSignupButtonEnable,
            )
        }
    }
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W700,
        fontSize = 26.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.26.sp,
    )
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}

@Preview(name = "LightThemePreview")
@Preview(name = "DarkThemePreview", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignupTitlePreview() {
    SignupTheme {
        Surface {
            Title(text = "테스트")
        }
    }
}