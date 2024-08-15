package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onFormValidationResult: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    var isUserNameValid by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }

    val isFormValid by remember(isUserNameValid, isEmailValid, isPasswordValid) {
        derivedStateOf { isUserNameValid && isEmailValid && isPasswordValid }
    }

    LaunchedEffect(isFormValid) {
        onFormValidationResult(isFormValid)
    }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        UserNameTextField(
            userName = userName,
            onUserNameChange = onUserNameChange,
            onValidationResult = { isUserNameValid = it }
        )

        EmailTextField(
            email = email,
            onEmailChange = onEmailChange,
            onValidationResult = { isEmailValid = it }
        )

        PasswordComponent(
            password = password,
            passwordConfirm = passwordConfirm,
            onPasswordChange = onPasswordChange,
            onPasswordConfirmChange = onPasswordConfirmChange,
            onValidationResult = { isPasswordValid = it }
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
        onPasswordConfirmChange = {},
        onFormValidationResult = {})
}