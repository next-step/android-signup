package nextstep.signup.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.model.InputValidator
import nextstep.signup.model.InputValidator.isValid
import nextstep.signup.model.TextFieldState
import nextstep.signup.model.TextFieldType
import nextstep.signup.view.ui.theme.Dimens.EndPadding
import nextstep.signup.view.ui.theme.Dimens.LargePadding
import nextstep.signup.view.ui.theme.Dimens.StartPadding
import nextstep.signup.view.ui.theme.Dimens.TopPadding
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    var isUsernameValid by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }
    var isMatchPassword by remember { mutableStateOf(true) }

    var usernameState: TextFieldState by remember { mutableStateOf(TextFieldState.Default) }
    var emailState: TextFieldState by remember { mutableStateOf(TextFieldState.Default) }
    var passwordState: TextFieldState by remember { mutableStateOf(TextFieldState.Default) }

    val isFormValid =
        isUsernameValid && isEmailValid && isPasswordValid && isMatchPassword

    SignupTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = TopPadding,
                        start = StartPadding,
                        end = EndPadding
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignUpTitleText()
                Spacer(modifier = Modifier.height(LargePadding))
                UsernameContent(
                    username,
                    onValueChange = { value: String ->
                        username = value
                        usernameState = isValid(
                            value,
                            TextFieldType.Username
                        )
                        isUsernameValid = usernameState == TextFieldState.Valid
                    },
                    usernameState,
                )
                Spacer(modifier = Modifier.height(LargePadding))
                EmailContent(
                    email,
                    onValueChange = { value: String ->
                        email = value
                        emailState = isValid(
                            value,
                            TextFieldType.Email
                        )
                        isEmailValid = emailState == TextFieldState.Valid
                    },
                    emailState,
                )
                Spacer(modifier = Modifier.height(LargePadding))
                PasswordContent(
                    password,
                    onValueChange = { value: String ->
                        password = value
                        passwordState = isValid(
                            value,
                            TextFieldType.Password
                        )
                        isPasswordValid = passwordState == TextFieldState.Valid
                        isMatchPassword = password == passwordConfirm
                    },
                    passwordState,
                )
                Spacer(modifier = Modifier.height(LargePadding))
                PasswordConfirmTextField(
                    onTextChange = {
                        passwordConfirm = it
                        isMatchPassword = password == passwordConfirm
                    },
                    isMatchPassword,
                )
                Spacer(modifier = Modifier.height(LargePadding))
                SignUpButton(isFormValid)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}