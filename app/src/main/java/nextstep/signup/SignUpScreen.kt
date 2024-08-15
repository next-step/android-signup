package nextstep.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.component.SignUpButton
import nextstep.signup.component.SignUpFormComponent
import nextstep.signup.component.SignUpTitleText
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    val (userName, setUserName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 32.dp, end = 32.dp), snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(42.dp)
        ) {
            SignUpTitleText(text = stringResource(id = R.string.sign_up_text_title))

            SignUpFormComponent(userName = userName,
                email = email,
                password = password,
                passwordConfirm = passwordConfirm,
                onUserNameChange = setUserName,
                onEmailChange = setEmail,
                onPasswordChange = setPassword,
                onPasswordConfirmChange = setPasswordConfirm,
                onFormValidationResult = { result ->
                    isFormValid = result
                })

            SignUpButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(context.getString(R.string.sign_up_complete))
                    }
                },
                enabled = isFormValid
            )
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}