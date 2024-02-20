package nextstep.signup.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.domain.InputEmail
import nextstep.signup.domain.InputPassword
import nextstep.signup.domain.InputUsername
import nextstep.signup.signup.component.EmailInputField
import nextstep.signup.signup.component.PasswordConfirmInputField
import nextstep.signup.signup.component.PasswordInputField
import nextstep.signup.signup.component.SignUpHeader
import nextstep.signup.signup.component.SignUpSubmitButton
import nextstep.signup.signup.component.UsernameInputField

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 60.dp),
        verticalArrangement = Arrangement.spacedBy(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var inputUsernameInputState: InputUsername? by remember { mutableStateOf(null) }
        var isUsernameValidState: Boolean by remember { mutableStateOf(false) }

        var emailInputState: InputEmail? by remember { mutableStateOf(null) }
        var isEmailValidState: Boolean by remember { mutableStateOf(false) }

        var passwordInputState: InputPassword? by remember { mutableStateOf(null) }
        var isPasswordValidState: Boolean by remember { mutableStateOf(false) }

        var passwordConfirmInputState by remember { mutableStateOf("") }
        var isPasswordMatchedState by remember { mutableStateOf(false) }

        SignUpHeader()

        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            UsernameInputField(
                inputUsername = inputUsernameInputState,
                onInputChanged = { username ->
                    inputUsernameInputState = username
                    isUsernameValidState = username.isValid()
                },
            )

            EmailInputField(
                inputEmail = emailInputState,
                onInputChanged = { email ->
                    emailInputState = email
                    isEmailValidState = email.isValid
                }
            )

            PasswordInputField(
                inputPassword = passwordInputState,
                onInputChanged = { password ->
                    passwordInputState = password
                    isPasswordValidState = password.isValid()
                }
            )

            PasswordConfirmInputField(
                value = passwordConfirmInputState,
                passwordToCompare = passwordInputState?.text.orEmpty(),
                onTextChanged = { passwordConfirm, isPasswordMatched ->
                    passwordConfirmInputState = passwordConfirm
                    isPasswordMatchedState = isPasswordMatched
                }
            )
        }

        SignUpSubmitButton(
            onClick = {},
            enabled = isUsernameValidState
                    && isEmailValidState
                    && isPasswordValidState
                    && isPasswordMatchedState,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
