package nextstep.signup.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.component.SignupButton
import nextstep.signup.ui.component.TextField
import nextstep.signup.ui.component.TitleText

@Composable
fun SignupScreen() {
    val name = remember {
        mutableStateOf("")
    }
    val nameEntered = remember {
        mutableStateOf(false)
    }
    val email = remember {
        mutableStateOf("")
    }
    val emailEntered = remember {
        mutableStateOf(false)
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordEntered = remember {
        mutableStateOf(false)
    }
    val passwordConfirm = remember {
        mutableStateOf("")
    }
    val passwordConfirmEntered = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TitleText()
            TextField(
                label = R.string.signup_username,
                input = name,
                inputEntered = nameEntered
            )
            TextField(
                label = R.string.signup_email,
                input = email,
                inputEntered = emailEntered
            )
            TextField(
                label = R.string.signup_password,
                input = password,
                inputEntered = passwordEntered
            )
            TextField(
                label = R.string.signup_password_confirm,
                input = passwordConfirm,
                inputEntered = passwordConfirmEntered
            )
            SignupButton()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}