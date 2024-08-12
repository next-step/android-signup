package nextstep.signup.feature.signup

import android.util.Log
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

            var isUsernameValid by remember { mutableStateOf(false) }
            var isEmailValid by remember { mutableStateOf(false) }
            var isPasswordValid by remember { mutableStateOf(false) }

            Text(
                text = stringResource(R.string.sign_up_title),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.height(42.dp))
            UsernameInputField(
                username = username,
                onValueChange = { username = it },
                onValidationSuccess = {
                    Log.e("dino_log", "isUsernameValid: $it")
                    isUsernameValid = it
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailInputField(
                email = email,
                onValueChange = { email = it },
                onValidationSuccess = {
                    Log.e("dino_log", "isEmailValid: $it")
                    isEmailValid = it
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordInputField(
                password = password,
                onValueChange = { password = it },
                onValidationSuccess = {
                    Log.e("dino_log", "isPasswordValid: $it")
                    isPasswordValid = it
                },
                space = 16.dp,
            )
            Spacer(modifier = Modifier.height(42.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
                enabled = isUsernameValid && isEmailValid && isPasswordValid,
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
