package nextstep.signup.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpContents(
    nameInputText: String,
    nameErrorMessage: String,
    emailInputText: String,
    emailErrorMessage: String,
    passwordInputText: String,
    passwordErrorMessage: String,
    passwordConfirmInputText: String,
    passwordConfirmErrorMessage: String,
    buttonIsEnabled: Boolean,
    onShowSnackbar: (String) -> Unit,
    onNameValueChange: (String) -> Unit,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onPasswordConfirmValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current


        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_name),
            keyboardType = KeyboardType.Text,
            inputText = nameInputText,
            onValueChange = { newText ->
                onNameValueChange(newText)
            },
            errorMessage = nameErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_email),
            keyboardType = KeyboardType.Text,
            inputText = emailInputText,
            onValueChange = { newText ->
                onEmailValueChange(newText)
            }, errorMessage = emailErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            inputText = passwordInputText,
            onValueChange = { newText ->
                onPasswordValueChange(newText)
            },
            errorMessage = passwordErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password_confirm),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            inputText = passwordConfirmInputText,
            onValueChange = { newText ->
                onPasswordConfirmValueChange(newText)
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

@Preview(showBackground = true)
@Composable
private fun SignUpContentsPreview() {
    SignupTheme {
        SignUpContents(
            onShowSnackbar = { message ->
            },
            nameInputText = "Isabel Robertson",
            nameErrorMessage = "Dale Merrill",
            emailInputText = "ora.joyner@example.com",
            emailErrorMessage = "lidia.reed@example.com",
            passwordInputText = "detracto",
            passwordErrorMessage = "omittantur",
            passwordConfirmInputText = "sea",
            passwordConfirmErrorMessage = "facilisi",
            buttonIsEnabled = false,
            onNameValueChange = {},
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onPasswordConfirmValueChange = {},
            modifier = Modifier
        )
    }
}