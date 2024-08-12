package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SingUpScreen() {
    val userName = remember { mutableStateOf("" ) }
    val email = remember { mutableStateOf("" ) }
    val password = remember { mutableStateOf("" ) }
    val passwordConfirm = remember { mutableStateOf("" ) }

    val isNameError = remember { mutableStateOf(true) }
    val isEmailError = remember { mutableStateOf(true) }
    val isPasswordError = remember { mutableStateOf(true) }
    val isPasswordConfirmError = remember { mutableStateOf(true) }

    val isFormValid by remember {
        derivedStateOf {
            !isNameError.value && !isEmailError.value && !isPasswordError.value && !isPasswordConfirmError.value
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 62.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TitleScreen("Welcome to Compose \uD83D\uDE80")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NameTextFieldScreen(
                    "Username",
                    userName.value,
                    onValueChange = {
                        userName.value = it
                    },
                    onValidChanged =  {
                        isNameError.value = it
                    }
                )
                EmailTextFieldScreen(
                    "Email",
                    email.value,
                    onValueChange = {
                        email.value = it
                    },
                    onValidChanged =  {
                        isEmailError.value = it
                    }
                )
                PasswordTextFieldScreen(
                    "Password",
                    password.value,
                    onValueChange = {
                        password.value = it
                    },
                    onValidChanged =  {
                        isPasswordError.value = it
                    }
                )
                PasswordConfirmTextFieldScreen(
                    "Password Confirm",
                    passwordConfirm.value,
                    password.value,
                    onValueChange = {
                        passwordConfirm.value = it
                    },
                    onValidChanged =  {
                        isPasswordConfirmError.value = it
                    }
                )
                SingUpButtonScreen(
                    Modifier.padding(16.dp),
                    enabled = isFormValid,
                )
            }
        },
        modifier = Modifier.padding(horizontal = 33.dp)
    )
}

