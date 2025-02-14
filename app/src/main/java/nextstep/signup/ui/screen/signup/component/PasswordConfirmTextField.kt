package nextstep.signup.ui.screen.signup.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.component.SignupTextField

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
        errorMessage = when (passwordConfirmValidationState) {
            is ValidationState.Error -> stringResource(passwordConfirmValidationState.resourceId)
            ValidationState.None -> null
        },
        modifier = modifier
    )
}
