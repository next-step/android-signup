package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.component.textField.EmailTextField
import nextstep.signup.component.textField.UserNameTextField

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
) {

    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        UserNameTextField(
            userName = userName, onUserNameChange = onUserNameChange
        )

        EmailTextField(
            email = email, onEmailChange = onEmailChange
        )

        PasswordComponent(
            password = password,
            passwordConfirm = passwordConfirm,
            onPasswordChange = onPasswordChange,
            onPasswordConfirmChange = onPasswordConfirmChange
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
        onPasswordConfirmChange = {})
}