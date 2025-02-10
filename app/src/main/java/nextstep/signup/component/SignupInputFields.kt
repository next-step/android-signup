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

@Composable
internal fun SignupInputFields(
    onValidation: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (userName, setUserName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

    var signupValidations by remember { mutableStateOf(SignupValidations()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UserNameTextFiled(
            text = userName,
            onValueChange = setUserName,
            onValidation = {
                signupValidations = signupValidations.copy(isUserNamePassed = it)
                onValidation(signupValidations.isAllValidation())
            },
        )
        EmailTextFiled(
            text = email,
            onValueChange = setEmail,
            onValidation = {
                signupValidations = signupValidations.copy(isEmailPassed = it)
                onValidation(signupValidations.isAllValidation())
            },
        )
        PasswordTextFiled(
            text = password,
            onValueChange = setPassword,
            onValidation = {
                signupValidations = signupValidations.copy(isPasswordPassed = it)
                onValidation(signupValidations.isAllValidation())
            },
        )
        PasswordConfirmTextFiled(
            text = passwordConfirm,
            passwordProvider = { password },
            onValueChange = setPasswordConfirm,
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
