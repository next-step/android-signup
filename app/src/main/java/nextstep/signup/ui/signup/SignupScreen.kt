package nextstep.signup.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.SignUpTextField
import nextstep.signup.ui.signup.SignupValidator.Email
import nextstep.signup.ui.signup.SignupValidator.Password
import nextstep.signup.ui.signup.SignupValidator.PasswordConfirm
import nextstep.signup.ui.signup.SignupValidator.Username
import nextstep.signup.ui.theme.RobotoBold

@Composable
fun SignUpScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 32.dp),
    ) {
        val (userName, setUserName) = remember { mutableStateOf("") }
        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = stringResource(R.string.signup_header_title),
            color = Color.Black,
            fontSize = 26.sp,
            fontFamily = RobotoBold,
        )
        Spacer(modifier = Modifier.height(42.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_user_name),
            onTextChanged = setUserName,
            text = userName,
            inputValidation = userName.isValid<Username>(),
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_email),
            onTextChanged = setEmail,
            text = email,
            inputValidation = email.isValid<Email>(),
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_password),
            onTextChanged = setPassword,
            text = password,
            visualTransformation = PasswordVisualTransformation(),
            inputValidation = password.isValid<Password>(),
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_password_confirm),
            onTextChanged = setPasswordConfirm,
            text = passwordConfirm,
            visualTransformation = PasswordVisualTransformation(),
            inputValidation = passwordConfirm.isValid<PasswordConfirm>(password),
        )
        Spacer(modifier = Modifier.height(42.dp))
        SignUpButton()
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
