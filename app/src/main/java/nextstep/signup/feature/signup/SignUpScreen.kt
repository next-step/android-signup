package nextstep.signup.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.inputField.EmailInputField
import nextstep.signup.ui.component.inputField.PasswordInputField
import nextstep.signup.ui.component.inputField.UsernameInputField

@Composable
fun SignUpScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(32.dp),
        ) {
            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordConfirm by remember { mutableStateOf("") }

            Text(
                text = stringResource(R.string.sign_up_title),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.height(42.dp))
            UsernameInputField(
                username = username,
                onValueChange = { username = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailInputField(
                email = email,
                onValueChange = { email = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordInputField(
                modifier = Modifier.testTag("password"),
                value = password,
                onValueChange = { password = it },
                hint = stringResource(R.string.sign_up_password),
            )
            Spacer(modifier = Modifier.height(36.dp))
            PasswordInputField(
                modifier = Modifier.testTag("passwordConfirm"),
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                hint = stringResource(R.string.sign_up_password_confirm),
            )
            Spacer(modifier = Modifier.height(42.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
            ) {
                Text(text = stringResource(R.string.sign_up_button))
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
