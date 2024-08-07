package nextstep.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.SignUpTextField
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Column(
        modifier =
        modifier
            .fillMaxSize()
            .padding(32.dp),
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_title),
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
        )

        SignUpTextField(
            value = userName,
            onValueChange = { value ->
                userName = value
            },
            label = { Text(text = stringResource(id = R.string.sign_up_label_username)) },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        SignUpTextField(
            value = email,
            onValueChange = { value ->
                email = value
            },
            label = { Text(text = stringResource(id = R.string.sign_up_label_email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        SignUpTextField(
            value = password,
            onValueChange = { value ->
                password = value
            },
            label = { Text(text = stringResource(id = R.string.sign_up_label_password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        SignUpTextField(
            value = passwordConfirm,
            onValueChange = { value ->
                passwordConfirm = value
            },
            label = { Text(text = stringResource(id = R.string.sign_up_label_password_confirm)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        Button(
            onClick = { /*TODO*/ },
            colors =
            ButtonDefaults.buttonColors(
                containerColor = Blue50,
                disabledContainerColor = Color(0x1D1820),
            ),
            modifier =
            Modifier
                .padding(top = 42.dp)
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text(
                text = stringResource(id = R.string.button_sign_up),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignupTheme {
        SignUpScreen()
    }
}
