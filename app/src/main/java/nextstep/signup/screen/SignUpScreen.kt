package nextstep.signup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.component.EmailTextField
import nextstep.signup.component.PasswordTextField
import nextstep.signup.component.SignUpTextField
import nextstep.signup.component.UserNameTextField
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme

enum class SignUpTextFieldType {
    UserName, Email, Password
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 60.dp, bottom = 6.dp),
                text = stringResource(id = R.string.sign_up_title),
                style = MaterialTheme.typography.headlineMedium
            )
            UserNameTextField(
                modifier = Modifier.fillMaxWidth(),
                text = userName,
                onValueChange = { value ->
                    userName = value
                },
            )
            EmailTextField(
                modifier = Modifier.fillMaxWidth(),
                text = email,
                onValueChange = { value ->
                    email = value
                }
            )
            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                text = password,
                onValueChange = { value ->
                    password = value
                },
            )
            SignUpTextField(
                modifier = Modifier.fillMaxWidth(),
                text = passwordConfirm,
                onValueChange = { value ->
                    passwordConfirm = value
                },
                labelText = stringResource(id = R.string.sign_up_password_confirm_label),
                visualTransformation = PasswordVisualTransformation()
            )
            TextButton(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Blue50,
                    contentColor = Color.White
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_button)
                )
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
