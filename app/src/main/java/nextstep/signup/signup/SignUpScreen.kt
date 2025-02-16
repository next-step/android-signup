package nextstep.signup.signup

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.signup.component.SignUpContents
import nextstep.signup.signup.component.SignUpTitle
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordMatch
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var nameInputText by remember { mutableStateOf("") }
    val nameErrorMessage by remember {
        derivedStateOf {
            getSignUpInputFieldErrorMessage(context, isNameValid(nameInputText))
        }
    }

    var emailInputText by remember { mutableStateOf("") }
    val emailErrorMessage by remember {
        derivedStateOf {
            getSignUpInputFieldErrorMessage(context, isEmailValid(emailInputText))
        }
    }
    var passwordInputText by remember { mutableStateOf("") }
    val passwordErrorMessage by remember {
        derivedStateOf {
            getSignUpInputFieldErrorMessage(context, isPasswordValid(passwordInputText))
        }
    }
    var passwordConfirmInputText by remember { mutableStateOf("") }
    val passwordConfirmErrorMessage by remember {
        derivedStateOf {
            getSignUpInputFieldErrorMessage(
                context,
                isPasswordMatch(passwordInputText, passwordConfirmInputText)
            )
        }
    }
    val buttonIsEnabled by remember {
        derivedStateOf {
            nameErrorMessage.isEmpty() && emailErrorMessage.isEmpty() && passwordErrorMessage.isEmpty() && passwordConfirmErrorMessage.isEmpty()
                    && nameInputText.isNotEmpty() && emailInputText.isNotEmpty() && passwordInputText.isNotEmpty() && passwordConfirmInputText.isNotEmpty()
        }
    }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            SignUpTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 42.dp),
                title = stringResource(R.string.signup_main_title)
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { contentPadding ->
        SignUpContents(
            onShowSnackbar = { message ->
                scope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            },
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 32.dp),
            nameInputText = nameInputText,
            nameErrorMessage = nameErrorMessage,
            emailInputText = emailInputText,
            emailErrorMessage = emailErrorMessage,
            passwordInputText = passwordInputText,
            passwordErrorMessage = passwordErrorMessage,
            passwordConfirmInputText = passwordConfirmInputText,
            passwordConfirmErrorMessage = passwordConfirmErrorMessage,
            buttonIsEnabled = buttonIsEnabled,
            onNameValueChange = { newText -> nameInputText = newText },
            onEmailValueChange = { newText -> emailInputText = newText },
            onPasswordValueChange = { newText -> passwordInputText = newText },
            onPasswordConfirmValueChange = { newText -> passwordConfirmInputText = newText },


            )
    }
}

fun getSignUpInputFieldErrorMessage(
    context: Context,
    validationType: SignUpValidationType
): String {
    return when (validationType) {
        SignUpValidationType.USERNAME_LENGTH_ERROR -> context.resources.getString(R.string.error_username_length)
        SignUpValidationType.USERNAME_FORMAT_ERROR -> context.resources.getString(R.string.error_username_format)
        SignUpValidationType.EMAIL_FORMAT_ERROR -> context.resources.getString(R.string.error_email_format)
        SignUpValidationType.PASSWORD_LENGTH_ERROR -> context.resources.getString(R.string.error_password_length)
        SignUpValidationType.PASSWORD_FORMAT_ERROR -> context.resources.getString(R.string.error_password_format)
        SignUpValidationType.PASSWORD_MISMATCH_ERROR -> context.resources.getString(R.string.error_password_mismatch)
        SignUpValidationType.VALID -> "" // 정상 상태일 때는 빈 문자열 반환
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}
