package nextstep.signup.userregister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.UserRegisterState
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.userregister.widget.EmailInputField
import nextstep.signup.userregister.widget.PasswordConfirmInputField
import nextstep.signup.userregister.widget.PasswordInputField
import nextstep.signup.userregister.widget.UserRegisterButton
import nextstep.signup.userregister.widget.UserRegisterTitle
import nextstep.signup.userregister.widget.UsernameInputField

@Composable
fun UserRegisterScreen(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(UserRegisterState()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        UserRegisterTitle()
        UsernameInputField(
            value = state.userName,
            onValueChange = { state = state.copy(userName = it) },
            modifier = Modifier.width(296.dp),
        )
        EmailInputField(
            value = state.email,
            onValueChange = { state = state.copy(email = it) },
            modifier = Modifier.width(296.dp),
        )
        PasswordInputField(
            value = state.password,
            onValueChange = { state = state.copy(password = it) },
            modifier = Modifier.width(296.dp),
        )
        PasswordConfirmInputField(
            password = state.password,
            passwordConfirm = state.passwordConfirm,
            onValueChange = { state = state.copy(passwordConfirm = it) },
            modifier = Modifier.width(296.dp),
        )
        UserRegisterButton(
            state = state,
            onButtonClick = {},
            modifier = Modifier.width(296.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserRegisterScreenPreview() {
    SignupTheme {
        UserRegisterScreen()
    }
}
