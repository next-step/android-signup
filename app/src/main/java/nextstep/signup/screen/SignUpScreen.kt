package nextstep.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.view.EmailTextField
import nextstep.signup.view.SignUpButton
import nextstep.signup.view.SignUpBasicTextField
import nextstep.signup.view.SignUpTitle
import nextstep.signup.view.UserNameTextField

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignUpTitle(
            text = stringResource(R.string.signup_title),
            modifier = Modifier.padding(top = 80.dp)
        )

        UserNameTextField(
            modifier = Modifier.padding(top = 40.dp),
            userName = userName,
            onValueChange = {
                userName = it
            }
        )

        EmailTextField(
            modifier = Modifier.padding(top = 36.dp),
            value = email,
            onValueChange = {
                email = it
            },
        )

        SignUpBasicTextField(
            modifier = Modifier.padding(top = 36.dp),
            label = stringResource(R.string.signup_password),
            value = password,
            onValueChange = {
                password = it
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )

        SignUpBasicTextField(
            modifier = Modifier.padding(top = 36.dp),
            label = stringResource(R.string.signup_confirm_password),
            value = passwordConfirm,
            onValueChange = {
                passwordConfirm = it
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        SignUpButton(
            modifier = Modifier.padding(top = 40.dp),
            text = stringResource(R.string.signup_button),
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun SignUpScreen_Preview() {
    SignUpScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}