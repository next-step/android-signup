package nextstep.signup.signup.component

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.signup.SignUpValidationType
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordMatch
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpContents(onShowSnackbar: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_name),
            keyboardType = KeyboardType.Text,
            inputText = nameInputText,
            onValueChange = { newTextFieldValue ->
                nameInputText = newTextFieldValue
            },
            errorMessage = nameErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_email),
            keyboardType = KeyboardType.Text,
            inputText = emailInputText,
            onValueChange = { newTextFieldValue ->
                emailInputText = newTextFieldValue
            }, errorMessage = emailErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            inputText = passwordInputText,
            onValueChange = { newTextFieldValue ->
                passwordInputText = newTextFieldValue
            },
            errorMessage = passwordErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password_confirm),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            inputText = passwordConfirmInputText,
            onValueChange = { newTextFieldValue ->
                passwordConfirmInputText = newTextFieldValue
            },
            errorMessage = passwordConfirmErrorMessage
        )

        SignUpButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            buttonTitle = stringResource(R.string.signup_main_signtup_button),
            enabled = buttonIsEnabled,
            onClick = { onShowSnackbar(context.getString(R.string.signup_finish)) })
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

@Preview(showBackground = true)
@Composable
private fun SignUpContentsPreview() {
    SignupTheme {
        SignUpContents(onShowSnackbar = { message ->
        })
    }
}