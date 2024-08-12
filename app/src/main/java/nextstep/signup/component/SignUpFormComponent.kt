package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.component.textField.EmailTextField
import nextstep.signup.component.textField.PasswordConfirmTextField
import nextstep.signup.component.textField.PasswordTextField
import nextstep.signup.component.textField.UserNameTextField
import nextstep.signup.valid.RegexBasedSignUpValidator
import nextstep.signup.valid.SignUpValidator

@Composable
fun SignUpFormComponent(
    userName: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    validator: SignUpValidator = RegexBasedSignUpValidator()
) {
    val userNameState by remember(userName) {
        derivedStateOf { validator.validateUsername(userName) }
    }
    val emailState by remember(email) {
        derivedStateOf { validator.validateEmail(email) }
    }
    val passwordState by remember(password) {
        derivedStateOf { validator.validatePassword(password) }
    }
    val passwordConfirmState by remember(password, passwordConfirm) {
        derivedStateOf { validator.validatePasswordConfirm(password, passwordConfirm) }
    }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        UserNameTextField(
            userName = userName, onUserNameChange = onUserNameChange, userNameState = userNameState
        )

        EmailTextField(
            email = email, onEmailChange = onEmailChange, emailState = emailState
        )

        PasswordTextField(
            password = password, onPasswordChange = onPasswordChange, passwordState = passwordState
        )

        PasswordConfirmTextField(
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChange = onPasswordConfirmChange,
            passwordConfirmState = passwordConfirmState
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpFormComponentPreview() {
    SignUpFormComponent(
        userName = "JohnDoe",
        email = "john@example.com",
        password = "Password123",
        passwordConfirm = "Password123",
        onUserNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onPasswordConfirmChange = {}
    )
}