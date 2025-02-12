package nextstep.signup.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.component.SignupSnackbar
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val hostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // String Resources
    val title = stringResource(R.string.signup_title)
    val usernameLabel = stringResource(R.string.signup_label_username)
    val emailLabel = stringResource(R.string.signup_label_email)
    val passwordLabel = stringResource(R.string.signup_label_password)
    val passwordConfirmLabel = stringResource(R.string.signup_label_password_confirm)
    val signUpButton = stringResource(R.string.signup_button)

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
    val isPasswordMatch by remember {
        derivedStateOf { SignupValidator.validatePasswordMatch(password, passwordConfirm) }
    }

    val enabled by remember {
        derivedStateOf {
            usernameValidation == SignupValidator.ResultType.Success &&
                    emailValidation == SignupValidator.ResultType.Success &&
                    passwordValidation == SignupValidator.ResultType.Success &&
                    passwordConfirmValidation == SignupValidator.ResultType.Success
        }
    }

    // Error Messages
    val usernameError by remember {
        derivedStateOf { SignupValidator.getErrorMessage(context, usernameValidation) }
    }
    val emailError by remember {
        derivedStateOf { SignupValidator.getErrorMessage(context, emailValidation) }
    }
    val passwordError by remember {
        derivedStateOf { SignupValidator.getErrorMessage(context, passwordValidation) }
    }
    val passwordConfirmError by remember {
        derivedStateOf { SignupValidator.getErrorMessage(context, passwordConfirmValidation) }
    }
    val passwordMismatchError by remember {
        derivedStateOf { SignupValidator.getErrorMessage(context, isPasswordMatch) }
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
            // Title
            SignupTitle(
                title = title,
                modifier = Modifier.padding(top = 60.dp)
            )

            // Username
            SignupTextField(
                label = usernameLabel,
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.padding(top = 23.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                isError = usernameError.isNotEmpty(),
                errorMessage = usernameError
            )

            // Email
            SignupTextField(
                label = emailLabel,
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                isError = emailError.isNotEmpty(),
                errorMessage = emailError
            )

            // Password
            SignupTextField(
                label = passwordLabel,
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                isError = passwordError.isNotEmpty(),
                errorMessage = passwordError
            )

            // Password Confirm
            SignupTextField(
                label = passwordConfirmLabel,
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                isError = passwordConfirmError.isNotEmpty(),
                errorMessage = passwordConfirmError
            )

            // Signup Button
            SignupButton(
                text = signUpButton,
                onClick = {
                    keyboardController?.hide()
                    coroutineScope.launch {
                        hostState.showSnackbar(
                            message = if (isPasswordMatch == SignupValidator.ResultType.Success) "회원가입 완료" else passwordMismatchError
                        )
                    }
                },
                modifier = Modifier.padding(top = 3.dp),
                enabled = enabled,
            )
        }
    }
}

@Composable
fun SignupTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
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

//@Preview
//@Composable
//fun SignupTitlePreview() {
//    SignupTitle(title = "테스트")
//}

@Preview(name = "LightThemePreview")
@Preview(name = "DarkThemePreview", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignupTitlePreview() {
    SignupTheme {
        Surface {
            SignupTitle(title = "테스트")
        }
    }
}