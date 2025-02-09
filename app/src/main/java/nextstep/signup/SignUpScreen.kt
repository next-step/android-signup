package nextstep.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.textfield.EmailTextField
import nextstep.signup.textfield.PasswordConfirmTextField
import nextstep.signup.textfield.PasswordTextField
import nextstep.signup.textfield.UsernameTextField
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
            .verticalScroll(rememberScrollState())
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

        UsernameTextField(
            value = userName,
            onValueChange = { userName = it }
        )

        EmailTextField(
            value = email,
            onValueChange = { email = it }
        )

        PasswordTextField(
            value = password,
            onValueChange = { password = it }
        )

        PasswordConfirmTextField(
            value = passwordConfirm,
            password = password,
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