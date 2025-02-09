package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.component.SubmitButton
import nextstep.signup.component.UserRegisterTextField
import nextstep.signup.component.Title
import nextstep.signup.util.ValidationUtil.setUsernameErrorMessage
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.util.ValidationUtil.setEmailErrorMessage
import nextstep.signup.util.ValidationUtil.setPasswordConfirmErrorMessage
import nextstep.signup.util.ValidationUtil.setPasswordErrorMessage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                UserRegisterScreen()
            }
        }
    }
}

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
        RegisterButton(
            onButtonClick = {},
            modifier = Modifier.width(296.dp),
        )
    }
}

@Composable
fun UserRegisterTitle(modifier: Modifier = Modifier) {
    Title(stringResource(R.string.user_register_screen_title), modifier)
}

@Composable
fun UsernameInputField(
    value: String,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = value,
        errorMessage = errorMessage,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_username_label),
        modifier = modifier,
    )
}

@Composable
fun EmailInputField(
    value: String,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = value,
        errorMessage = errorMessage,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_email_label),
        modifier = modifier,
    )
}

@Composable
fun PasswordInputField(
    value: String,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = value,
        errorMessage = errorMessage,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_password_label),
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun PasswordConfirmInputField(
    value: String,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = value,
        errorMessage = errorMessage,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_password_confirm_label),
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun RegisterButton(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SubmitButton(
        text = "Sign Up",
        onClickButton = onButtonClick,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun UserRegisterScreenPreview() {
    SignupTheme {
        UserRegisterScreen()
    }
}
