package nextstep.signup.userregister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.userregister.widget.EmailInputField
import nextstep.signup.userregister.widget.PasswordConfirmInputField
import nextstep.signup.userregister.widget.PasswordInputField
import nextstep.signup.userregister.widget.UserRegisterButton
import nextstep.signup.userregister.widget.UserRegisterTitle
import nextstep.signup.userregister.widget.UsernameInputField
import nextstep.signup.util.ValidationUtil.checkIsAllPassValidation
import nextstep.signup.util.ValidationUtil.setEmailErrorMessage
import nextstep.signup.util.ValidationUtil.setPasswordConfirmErrorMessage
import nextstep.signup.util.ValidationUtil.setPasswordErrorMessage
import nextstep.signup.util.ValidationUtil.setUsernameErrorMessage

@Composable
fun UserRegisterScreen(modifier: Modifier = Modifier) {
    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirm by rememberSaveable { mutableStateOf("") }

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
            value = userName,
            errorMessage = setUsernameErrorMessage(userName),
            onValueChange = { userName = it },
            modifier = Modifier.width(296.dp),
        )
        EmailInputField(
            value = email,
            errorMessage = setEmailErrorMessage(email),
            onValueChange = { email = it },
            modifier = Modifier.width(296.dp),
        )
        PasswordInputField(
            value = password,
            errorMessage = setPasswordErrorMessage(password),
            onValueChange = { password = it },
            modifier = Modifier.width(296.dp),
        )
        PasswordConfirmInputField(
            value = passwordConfirm,
            errorMessage = setPasswordConfirmErrorMessage(password, passwordConfirm),
            onValueChange = { passwordConfirm = it },
            modifier = Modifier.width(296.dp),
        )
        UserRegisterButton(
            onButtonClick = {},
            enabled = checkIsAllPassValidation(
                userName = userName,
                email = email,
                password = password,
                passwordConfirm = passwordConfirm,
            ),
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
