package nextstep.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50

@Preview(showBackground = true)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        SignUpTitle()
        Spacer(modifier = Modifier.height(height = 42.dp))
        UserNameTextField()
        Spacer(modifier = Modifier.height(height = 36.dp))
        EmailTextField()
        Spacer(modifier = Modifier.height(height = 36.dp))
        PasswordTextField()
        Spacer(modifier = Modifier.height(height = 36.dp))
        PasswordConfirmTextField()
        Spacer(modifier = Modifier.height(height = 42.dp))
        SignUpButton()
    }
}

@Preview
@Composable
private fun SignUpButton(modifier: Modifier = Modifier) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50
        ),
        onClick = {}
    ) {
        Text(text = stringResource(R.string.sign_up))
    }
}

@Preview
@Composable
private fun PasswordConfirmTextField(modifier: Modifier = Modifier) {
    var passwordConfirm by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 53.dp),
        value = passwordConfirm,
        onValueChange = { value ->
            passwordConfirm = value
        },
        label = {
            Text(stringResource(R.string.password_confirm))
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        )
    )
}

@Preview
@Composable
private fun PasswordTextField(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 53.dp),
        value = password,
        onValueChange = { value ->
            password = value
        },
        label = {
            Text(stringResource(R.string.password))
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        )
    )
}

@Preview
@Composable
private fun EmailTextField(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 53.dp),
        value = email,
        onValueChange = { value ->
            email = value
        },
        label = {
            Text(text = stringResource(R.string.email))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        )
    )
}

@Preview
@Composable
private fun UserNameTextField(modifier: Modifier = Modifier) {
    var userName by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 53.dp),
        value = userName,
        onValueChange = { value ->
            userName = value
        },
        label = {
            Text(text = stringResource(R.string.username))
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.welcome_to_compose),
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp,
    )
}
