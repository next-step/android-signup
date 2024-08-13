package nextstep.signup.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import nextstep.signup.ui.component.SignupButton
import nextstep.signup.ui.component.SignupTextField
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.SignupValidator.Email
import nextstep.signup.ui.signup.SignupValidator.Password
import nextstep.signup.ui.signup.SignupValidator.PasswordConfirm
import nextstep.signup.ui.signup.SignupValidator.Username
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.RobotoBold
import nextstep.signup.ui.theme.RobotoMedium

@Composable
fun SignupScreen(
    onSignupClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 32.dp),
    ) {
        val (userName, setUserName) = remember { mutableStateOf("") }
        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }
        val usernameValidation = userName.isValid<Username>()
        val emailValidation = email.isValid<Email>()
        val passwordValidation = password.isValid<Password>()
        val passwordConfirmValidation = passwordConfirm.isValid<PasswordConfirm>(password)
        val isValidOfSignup = usernameValidation == Success && emailValidation == Success
                && passwordValidation == Success && passwordConfirmValidation == Success

        Spacer(modifier = Modifier.height(60.dp))
        HeaderText()
        Spacer(modifier = Modifier.height(42.dp))
        UsernameTextField(
            username = userName,
            onUsernameChanged = setUserName,
            usernameValidation = usernameValidation,
        )
        Spacer(modifier = Modifier.height(36.dp))
        EmailTextField(
            email = email,
            onEmailChanged = setEmail,
            emailValidation = emailValidation,
        )
        Spacer(modifier = Modifier.height(36.dp))
        PasswordTextField(
            password = password,
            onPasswordChanged = setPassword,
            passwordValidation = passwordValidation,
        )
        Spacer(modifier = Modifier.height(36.dp))
        PasswordConfirmTextField(
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChanged = setPasswordConfirm,
            password = password,
            passwordConfirmValidation = passwordConfirmValidation,
        )
        Spacer(modifier = Modifier.height(42.dp))
        CreateAccountButton(
            isSuccessfulCondition = isValidOfSignup,
            onCreateAccountButtonClick = onSignupClick,
        )
    }
}

@Composable
private fun HeaderText() {
    Text(
        text = stringResource(R.string.signup_header_title),
        color = Color.Black,
        fontSize = 26.sp,
        fontFamily = RobotoBold,
    )
}

@Composable
private fun UsernameTextField(
    username: String,
    onUsernameChanged: (String) -> Unit,
    usernameValidation: SignupValidationResult,
) {
    SignupTextField(
        text = username,
        onTextChanged = onUsernameChanged,
        label = stringResource(R.string.signup_user_name),
        isError = usernameValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (usernameValidation !is Success) Text(
                text = (usernameValidation as Failure).result.message
            )
        },
    )
}

@Composable
private fun EmailTextField(
    email: String,
    onEmailChanged: (String) -> Unit,
    emailValidation: SignupValidationResult,
) {
    SignupTextField(
        text = email,
        onTextChanged = onEmailChanged,
        label = stringResource(R.string.signup_email),
        isError = emailValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (emailValidation !is Success) Text(
                text = (emailValidation as Failure).result.message
            )
        },
    )
}

@Composable
private fun PasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordValidation: SignupValidationResult,
) {
    SignupTextField(
        text = password,
        onTextChanged = onPasswordChanged,
        label = stringResource(R.string.signup_password),
        isError = passwordValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (passwordValidation !is Success) Text(
                text = (passwordValidation as Failure).result.message
            )
        },
    )
}

@Composable
fun PasswordConfirmTextField(
    passwordConfirm: String,
    onPasswordConfirmChanged: (String) -> Unit,
    password: String,
    passwordConfirmValidation: SignupValidationResult,
) {
    SignupTextField(
        text = passwordConfirm,
        onTextChanged = onPasswordConfirmChanged,
        label = stringResource(R.string.signup_password_confirm),
        isError = passwordConfirmValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (passwordConfirmValidation !is Success) Text(
                text = (passwordConfirmValidation as Failure).result.message
            )
        },
    )
}

@Composable
private fun CreateAccountButton(
    isSuccessfulCondition: Boolean,
    onCreateAccountButtonClick: () -> Unit,
) {
    SignupButton(
        buttonText = stringResource(R.string.signup_button),
        buttonTextFontSize = 14.sp,
        buttonTextFontFamily = RobotoMedium,
        buttonTextColor = if (isSuccessfulCondition) Color.White else Color.Black,
        buttonVerticalPadding = 15.dp,
        containerColor = if (isSuccessfulCondition) Blue50 else Color.Gray,
        onButtonClick = onCreateAccountButtonClick,
        enabled = isSuccessfulCondition,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun SignupScreenPreview() {
    SignupScreen(modifier = Modifier, onSignupClick = {})
}
