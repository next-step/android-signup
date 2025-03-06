package nextstep.signup.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.InputValidation
import nextstep.signup.R
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmationTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.UserNameTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }

    var usernameErrorResourceId: Int? by remember { mutableStateOf(null) }
    var emailErrorResourceId: Int? by remember { mutableStateOf(null) }
    var passwordErrorResourceId: Int? by remember { mutableStateOf(null) }
    var passwordConfirmationErrorResourceId: Int? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 76.dp),
            text = stringResource(R.string.header),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        UserNameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            value = username,
            onValueChange = {
                username = it
                usernameErrorResourceId = InputValidation.validateUserName(username)
            },
            errorMessage = usernameErrorResourceId?.let { stringResource(it) },
            label = stringResource(R.string.username),
            imeAction = ImeAction.Next
        )
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                emailErrorResourceId = InputValidation.validateEmail(email)
            },
            errorMessage = emailErrorResourceId?.let { stringResource(it) },
            label = stringResource(R.string.email),
            imeAction = ImeAction.Next
        )
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                passwordErrorResourceId = InputValidation.validatePassword(password)
            },
            errorMessage = passwordErrorResourceId?.let { stringResource(it) },
            label = stringResource(R.string.password),
            visualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Next
        )
        PasswordConfirmationTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordConfirmation,
            onValueChange = {
                passwordConfirmation = it
                passwordConfirmationErrorResourceId = InputValidation.validateConfirmPassword(
                    password = password,
                    confirmPassword = passwordConfirmation
                )
            },
            errorMessage = passwordConfirmationErrorResourceId?.let { stringResource(it) },
            label = stringResource(R.string.password_confirmation),
            visualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Done
        )

        SignUpButton(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    SignupTheme {
        SignUpScreen()
    }
}