package nextstep.signup.ui.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.ui.ValidationState

@Composable
fun PasswordConfirmTextField(
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit,
    passwordConfirmValidationState: ValidationState,
    modifier: Modifier = Modifier
) {
    SignupTextField(
        value = passwordConfirm,
        onValueChange = onPasswordConfirmChange,
        label = stringResource(R.string.password_confirm),
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Password
        ),
        secure = true,
        validationState = passwordConfirmValidationState,
        modifier = modifier
    )
}
