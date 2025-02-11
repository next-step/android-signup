package nextstep.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.utils.SignUpErrorType
import nextstep.signup.utils.SignUpValidator
import nextstep.signup.view.EmailTextField
import nextstep.signup.view.PasswordConfirmTextField
import nextstep.signup.view.PasswordTextField
import nextstep.signup.view.SignUpButton
import nextstep.signup.view.SignUpTitle
import nextstep.signup.view.UserNameTextField

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    showSnackBar: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    val isAllValid by remember(userName, email, password, passwordConfirm) {
        derivedStateOf {
            SignUpValidator.Username(userName).validate() == SignUpErrorType.NO_ERROR &&
                    SignUpValidator.Email(email).validate() == SignUpErrorType.NO_ERROR &&
                    SignUpValidator.Password(password).validate() == SignUpErrorType.NO_ERROR &&
                    SignUpValidator.PasswordConfirm(passwordConfirm, password).validate() == SignUpErrorType.NO_ERROR
        }
    }

    val isAllNotEmpty by remember(userName, email, password, passwordConfirm) {
        derivedStateOf {
            userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignUpTitle(
            text = stringResource(R.string.signup_title),
            modifier = Modifier.padding(top = 80.dp)
        )

        UserNameTextField(
            modifier = Modifier.padding(top = 40.dp),
            userName = userName,
            errorType = SignUpValidator.Username(userName).validate(),
            onValueChange = {
                userName = it
            }
        )

        EmailTextField(
            modifier = Modifier.padding(top = 36.dp),
            value = email,
            errorType = SignUpValidator.Email(email).validate(),
            onValueChange = {
                email = it
            },
        )

        PasswordTextField(
            modifier = Modifier.padding(top = 36.dp),
            value = password,
            errorType = SignUpValidator.Password(password).validate(),
            onValueChange = {
                password = it
            },
        )

        PasswordConfirmTextField(
            modifier = Modifier.padding(top = 36.dp),
            value = passwordConfirm,
            errorType = SignUpValidator.PasswordConfirm(
                value = passwordConfirm,
                password = passwordConfirm
            ).validate(),
            onValueChange = {
                passwordConfirm = it
            }
        )

        SignUpButton(
            modifier = Modifier.padding(top = 40.dp),
            text = stringResource(R.string.signup_button),
            enabled = isAllValid && isAllNotEmpty,
            onClick = {
                showSnackBar(context.getString(R.string.signup_success_snackbar_message))
            },
        )
    }
}

@Preview
@Composable
private fun SignUpScreen_Preview() {
    SignUpScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}