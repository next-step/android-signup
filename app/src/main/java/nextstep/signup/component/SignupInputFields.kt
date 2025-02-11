package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.model.SignupValidations
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun SignupInputFields(
    onValidation: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    var signupValidations by remember { mutableStateOf(SignupValidations()) }
    val passwordConfirmValidator =
        remember(password) { SignupInfoValidator.PasswordConfirm { password } }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UserNameTextFiled(
            text = userName,
            onValueChange = { userName = it },
            onValidation = {
                signupValidations = signupValidations.copy(isUserNamePassed = it)
                onValidation(signupValidations.isAllValidation())
            },
        )
        EmailTextFiled(
            text = email,
            onValueChange = { email = it },
            onValidation = {
                signupValidations = signupValidations.copy(isEmailPassed = it)
                onValidation(signupValidations.isAllValidation())
            },
        )
        PasswordTextFiled(
            text = password,
            onValueChange = { password = it },
            onValidation = {
                signupValidations = signupValidations.copy(isPasswordPassed = it)
                onValidation(signupValidations.isAllValidation())
            },
        )
        PasswordConfirmTextFiled(
            text = passwordConfirm,
            validator = passwordConfirmValidator,
            onValueChange = { passwordConfirm = it },
            onValidation = {
                signupValidations = signupValidations.copy(isPasswordConfirmPassed = it)
                onValidation(signupValidations.isAllValidation())
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupFieldsPreview() {
    SignupInputFields(onValidation = {})
}
