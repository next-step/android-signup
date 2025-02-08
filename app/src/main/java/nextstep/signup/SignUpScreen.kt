package nextstep.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 112.dp),
            text = stringResource(R.string.sign_up_title),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 20.sp,
            letterSpacing = 1.sp
        )

        SignUpTextField(
            value = userName,
            label = stringResource(R.string.sign_up_user_name),
            onValueChange = { userName = it }
        )

        SignUpTextField(
            value = email,
            label = stringResource(R.string.sign_up_email),
            onValueChange = { email = it }
        )

        SignUpTextField(
            value = password,
            label = stringResource(R.string.sign_up_password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it }
        )

        SignUpTextField(
            value = passwordConfirm,
            label = stringResource(R.string.sign_up_password_confirm),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { passwordConfirm = it }
        )

        SignUpButton(
            text = stringResource(R.string.sign_up),
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}