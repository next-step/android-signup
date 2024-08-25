package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.ui.SignUpButtonComponent
import nextstep.signup.ui.SignUpTextFieldComponent
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.validation.InputValidation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SignupTheme(dynamicColor = false) {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    var inputPassword = ""

    Scaffold(
        topBar = {
            Text(
                text = stringResource(R.string.title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 40.dp)
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                val userNameValidation = InputValidation.UserNameValidation(
                    stringResource(R.string.user_name_invalid_msg),
                    stringResource(R.string.user_name_invalid_length_msg),
                )

                SignUpTextFieldComponent(
                    stringResource(R.string.input_user_name),
                    { userNameValidation.checkValidation(it) }
                )

                val emailValidation = InputValidation.EmailValidation(
                    stringResource(R.string.email_invalid_msg)
                )

                SignUpTextFieldComponent(
                    stringResource(R.string.input_email),
                    { emailValidation.checkValidation(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                val passwordValidation = InputValidation.PasswordValidation(
                    stringResource(R.string.password_invalid_msg),
                    stringResource(R.string.password_invalid_length_msg)
                )

                SignUpTextFieldComponent(
                    stringResource(R.string.input_password),
                    {
                        inputPassword = it
                        passwordValidation.checkValidation(it)
                    },
                    PasswordVisualTransformation()
                )

                val passwordConfirmValidation = InputValidation.PasswordConfirmValidation(
                    inputPassword,
                    stringResource(R.string.password_confirm_invalid_msg)
                )

                SignUpTextFieldComponent(
                    stringResource(R.string.input_password_confirm),
                    { passwordConfirmValidation.checkValidation(it) },
                    PasswordVisualTransformation()
                )
                SignUpButtonComponent(btnText = stringResource(R.string.btn_sign_up)) { /* Handle Sign Up */ }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignupTheme(dynamicColor = false) {
        SignUpScreen()
    }
}
