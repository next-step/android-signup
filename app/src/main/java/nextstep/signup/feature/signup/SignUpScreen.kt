package nextstep.signup.feature.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun SignUpScreen() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(36.dp),
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
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    onValueChange = { username = it },
                    label = {
                        Text(text = stringResource(R.string.sign_up_username))
                    },
                    singleLine = true,
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(text = stringResource(R.string.sign_up_email))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(text = stringResource(R.string.sign_up_password))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordConfirm,
                    onValueChange = { passwordConfirm = it },
                    label = {
                        Text(text = stringResource(R.string.sign_up_password_confirm))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                ) {
                    Text(text = stringResource(R.string.sign_up_button))
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
