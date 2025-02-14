package nextstep.signup.ui.screen.signup

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.component.SignupButton
import nextstep.signup.ui.screen.signup.component.EmailTextField
import nextstep.signup.ui.screen.signup.component.PasswordConfirmTextField
import nextstep.signup.ui.screen.signup.component.PasswordTextField
import nextstep.signup.ui.screen.signup.component.UsernameTextField
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.ui.validateEmail
import nextstep.signup.ui.validatePassword
import nextstep.signup.ui.validatePasswordConfirm
import nextstep.signup.ui.validateUsername

@Composable
fun SignupScreen(
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    usernameValidationState: ValidationState,
    emailValidationState: ValidationState,
    passwordValidationState: ValidationState,
    passwordConfirmValidationState: ValidationState,
    isAllValidationPass: Boolean,
    showCompleteSnackbar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                top = 60.dp,
                start = 32.dp,
                end = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome_to_compose),
            fontWeight = FontWeight.W700,
            fontSize = 26.sp,
            lineHeight = 20.sp,
        )
        Spacer(modifier = Modifier.height(42.dp))
        TextFieldsContent(
            username = username,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            onUsernameChange = onUsernameChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onPasswordConfirmChange = onPasswordConfirmChange,
            usernameValidationState = usernameValidationState,
            emailValidationState = emailValidationState,
            passwordValidationState = passwordValidationState,
            passwordConfirmValidationState = passwordConfirmValidationState
        )
        Spacer(modifier = Modifier.height(39.dp))
        SignupButton(
            label = stringResource(R.string.signup),
            onClick = showCompleteSnackbar,
            enabled = isAllValidationPass,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}

@Composable
private fun TextFieldsContent(
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    usernameValidationState: ValidationState,
    emailValidationState: ValidationState,
    passwordValidationState: ValidationState,
    passwordConfirmValidationState: ValidationState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(33.dp)
    ) {
        UsernameTextField(
            modifier = Modifier.fillMaxWidth(),
            username = username,
            onUsernameChange = onUsernameChange,
            validationState = usernameValidationState
        )
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            email = email,
            onEmailChange = onEmailChange,
            validationState = emailValidationState
        )
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            password = password,
            onPasswordChange = onPasswordChange,
            validationState = passwordValidationState
        )
        PasswordConfirmTextField(
            modifier = Modifier.fillMaxWidth(),
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChange = onPasswordConfirmChange,
            passwordConfirmValidationState = passwordConfirmValidationState
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = Color.WHITE.toLong()
)
@Composable
private fun SignupScreenPreview() {
    SignupTheme {
        val username = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordConfirm = remember { mutableStateOf("") }

        val usernameValidation =
            remember(username) { derivedStateOf { validateUsername(username.value) } }
        val emailValidation =
            remember(email) { derivedStateOf { validateEmail(email.value) } }
        val passwordValidation =
            remember(password) { derivedStateOf { validatePassword(password.value) } }
        val passwordConfirmValidation = remember(password, passwordConfirm) {
            derivedStateOf {
                validatePasswordConfirm(password.value, passwordConfirm.value)
            }
        }

        val isAllValidationPass = remember(
            usernameValidation.value,
            emailValidation.value,
            passwordValidation.value,
            passwordConfirmValidation.value,
        ) {
            derivedStateOf {
                usernameValidation.value is ValidationState.None &&
                        emailValidation.value is ValidationState.None &&
                        passwordValidation.value is ValidationState.None &&
                        passwordConfirmValidation.value is ValidationState.None
            }
        }

        SignupScreen(
            modifier = Modifier.fillMaxSize(),
            username = username.value,
            email = email.value,
            password = password.value,
            passwordConfirm = passwordConfirm.value,
            onUsernameChange = {
                username.value = it
            },
            onEmailChange = {
                email.value = it
            },
            onPasswordChange = {
                password.value = it
            },
            onPasswordConfirmChange = {
                passwordConfirm.value = it
            },
            showCompleteSnackbar = {},
            usernameValidationState = usernameValidation.value,
            emailValidationState = emailValidation.value,
            passwordValidationState = passwordValidation.value,
            passwordConfirmValidationState = passwordConfirmValidation.value,
            isAllValidationPass = isAllValidationPass.value
        )
    }
}
