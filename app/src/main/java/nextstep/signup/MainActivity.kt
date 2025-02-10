package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import nextstep.signup.domain.Email
import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.Username
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.EmailState
import nextstep.signup.state.PasswordConfirmState
import nextstep.signup.state.PasswordState
import nextstep.signup.state.UsernameState
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var usernameState: UsernameState by remember { mutableStateOf(UsernameState()) }
            var email by remember { mutableStateOf(EmailState()) }
            var password by remember { mutableStateOf(PasswordState()) }
            var passwordConfirm by remember { mutableStateOf(PasswordConfirmState()) }

            SignupTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                ) { paddingValues ->
                    SignUpScreen(
                        username = usernameState.username,
                        isUsernameError = usernameState.isError,
                        usernameSupportingText = usernameState.supportingText,
                        onUserNameChange = { usernameState = Username(it).toUiState() },
                        email = email.email,
                        isEmailError = email.isError,
                        emailSupportingText = email.supportingText,
                        onEmailChange = { email = Email(it).toUiState() },
                        password = password.password,
                        isPasswordError = password.isError,
                        passwordSupportingText = password.supportingText,
                        onPasswordChange = {
                            password = Password(it).toUiState()
                            if (passwordConfirm.passwordConfirm.isNotEmpty()) {
                                passwordConfirm =
                                    PasswordConfirm(passwordConfirm.passwordConfirm).toUiState(it)
                            }
                        },
                        passwordConfirm = passwordConfirm.passwordConfirm,
                        isPasswordConfirmError = passwordConfirm.isError,
                        passwordConfirmSupportingText = passwordConfirm.supportingText,
                        onPasswordConfirmChange = {
                            passwordConfirm = PasswordConfirm(it).toUiState(password.password)
                        },
                        onSignUpButtonClick = {},
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    )
                }
            }
        }
    }
}
