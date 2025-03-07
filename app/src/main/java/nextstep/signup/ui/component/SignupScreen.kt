package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.component.textfield.EmailTextField
import nextstep.signup.ui.component.textfield.PasswordTextField
import nextstep.signup.ui.component.textfield.UsernameTextField
import nextstep.signup.ui.state.SignupScreenState

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onSignupComplete: () -> Unit = {},
) {
    val signupState = remember { SignupScreenState() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .padding(horizontal = 32.dp)
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        SignupTitle()
        UsernameTextField(
            inputValue = signupState.username,
            onInputChange = { signupState.updateUsername(it) },
            validResult = signupState.usernameValidation,
        )
        EmailTextField(
            inputValue = signupState.email,
            onInputChange = { signupState.updateEmail(it) },
            validResult = signupState.emailValidation,
        )
        PasswordTextField(
            label = stringResource(R.string.signup_field_label_password),
            inputValue = signupState.password,
            onInputChange = { signupState.updatePassword(it) },
            validResult = signupState.passwordValidation,
        )
        PasswordTextField(
            label = stringResource(R.string.signup_field_label_password_confirm),
            inputValue = signupState.passwordConfirm,
            onInputChange = { signupState.updatePasswordConfirm(it) },
            validResult = signupState.passwordConfirmValidation,
        )
        SubmitButton(
            enabled = signupState.isFormValid,
            onClick = {
                keyboardController?.hide()

                onSignupComplete()
            }
        )
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}