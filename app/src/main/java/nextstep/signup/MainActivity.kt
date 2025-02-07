package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import nextstep.signup.component.MyTextField
import nextstep.signup.component.Title
import nextstep.signup.ui.theme.SignupTheme

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
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        UserRegisterTitle()
        UsernameInputField(userName, { userName = it })
        EmailInputField(email, { email = it })
        PasswordInputField(password, { password = it })
        PasswordConfirmInputField(passwordConfirm, { passwordConfirm = it })
    }
}

@Composable
private fun UserRegisterTitle(modifier: Modifier = Modifier) {
    Title(stringResource(R.string.user_register_screen_title), modifier)
}

@Composable
private fun UsernameInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    MyTextField(
        value = value,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_username_label),
        modifier = modifier,
    )
}

@Composable
private fun EmailInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    MyTextField(
        value = value,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_email_label),
        modifier = modifier,
    )
}

@Composable
private fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    MyTextField(
        value = value,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_password_label),
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
private fun PasswordConfirmInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    MyTextField(
        value = value,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_password_confirm_label),
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun UserRegisterScreenPreview() {
    SignupTheme {
        UserRegisterScreen()
    }
}