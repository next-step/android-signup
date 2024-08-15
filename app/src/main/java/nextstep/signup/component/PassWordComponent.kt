package nextstep.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import nextstep.signup.component.textField.PasswordConfirmTextField
import nextstep.signup.component.textField.PasswordTextField
import nextstep.signup.model.PasswordConfirmState
import nextstep.signup.model.PasswordState
import nextstep.signup.valid.RegexBasedSignUpValidator
import nextstep.signup.valid.SignUpValidator

@Composable
fun PasswordComponent(
    password: String,
    passwordConfirm: String,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onValidationResult: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    validator: SignUpValidator = RegexBasedSignUpValidator()
) {
    val passwordState by remember(password) {
        derivedStateOf { validator.validatePassword(password) }
    }
    val passwordConfirmState by remember(password, passwordConfirm) {
        derivedStateOf { validator.validatePasswordConfirm(password, passwordConfirm) }
    }

    val isValid by remember(passwordState, passwordConfirmState) {
        derivedStateOf {
            passwordState is PasswordState.Valid &&
                    passwordConfirmState is PasswordConfirmState.Valid
        }
    }
    LaunchedEffect(isValid) {
        onValidationResult(isValid)
    }


    Column(modifier = modifier) {
        PasswordTextField(
            password = password,
            onPasswordChange = onPasswordChange,
            passwordState = passwordState
        )

        Spacer(modifier = Modifier.height(42.dp))

        PasswordConfirmTextField(
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChange = onPasswordConfirmChange,
            passwordConfirmState = passwordConfirmState
        )
    }
}

@Preview
@Composable
private fun PasswordComponentPreview() {
    PasswordComponent(
        password = "Password123",
        passwordConfirm = "Password123",
        onPasswordChange = {},
        onPasswordConfirmChange = {},
        onValidationResult = {}
    )
}