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
        var usernameInputState by remember { mutableStateOf("") }
        var isUsernameValidState by remember { mutableStateOf(true) }

        var emailInputState by remember { mutableStateOf("") }
        var isEmailValidState by remember { mutableStateOf(true) }

        var passwordInputState by remember { mutableStateOf("") }
        var isPasswordValidState by remember { mutableStateOf(true) }

        var passwordConfirmInputState by remember { mutableStateOf("") }
        var isPasswordMatchedState by remember { mutableStateOf(true) }

        SignUpHeader()

        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            UsernameInputField(
                value = usernameInputState,
                onTextChanged = { username, isUsernameValid ->
                    usernameInputState = username
                    isUsernameValidState = isUsernameValid
                },
            )

            EmailInputField(
                value = emailInputState,
                onTextChanged = { email, isEmailValid ->
                    emailInputState = email
                    isEmailValidState = isEmailValid
                }
            )

            PasswordInputField(
                value = passwordInputState,
                onTextChanged = { password, isPasswordValid ->
                    passwordInputState = password
                    isPasswordValidState = isPasswordValid
                }
            )

            PasswordConfirmInputField(
                value = passwordConfirmInputState,
                passwordToCompare = passwordInputState,
                onTextChanged = { passwordConfirm, isPasswordMatched ->
                    passwordConfirmInputState = passwordConfirm
                    isPasswordMatchedState = isPasswordMatched
                }
            )
        }

        SignUpSubmitButton(
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
