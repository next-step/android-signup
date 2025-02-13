package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.component.textfield.EmailTextField
import nextstep.signup.ui.component.textfield.PasswordTextField
import nextstep.signup.ui.component.textfield.UsernameTextField
import nextstep.signup.ui.util.EmailValidator
import nextstep.signup.ui.util.NameValidator
import nextstep.signup.ui.util.PasswordMatchValidator
import nextstep.signup.ui.util.PasswordValidator

@Composable
fun SignupScreen() {
    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 40.dp)
                .padding(horizontal = 32.dp)
        ) {
            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordConfirm by remember { mutableStateOf("") }

            val nameValidator = NameValidator()
            val emailValidator = EmailValidator()
            val passwordValidator = PasswordValidator()
            val passwordMatchValidator = PasswordMatchValidator(getEnteredPassword = { password })

            SignupTitle()
            UsernameTextField(
                inputValue = username,
                onInputChange = { username = it },
                validResult = nameValidator.validate(username),
            )
            EmailTextField(
                inputValue = email,
                onInputChange = { email = it },
                validResult = emailValidator.validate(email),
            )
            PasswordTextField(
                label = stringResource(R.string.signup_field_label_password),
                inputValue = password,
                onInputChange = { password = it },
                validResult = passwordValidator.validate(password),
            )
            PasswordTextField(
                label = stringResource(R.string.signup_field_label_password_confirm),
                inputValue = passwordConfirm,
                onInputChange = { passwordConfirm = it },
                validResult = passwordMatchValidator.validate(passwordConfirm),
            )
            SubmitButton()
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}