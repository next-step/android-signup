package nextstep.signup.ui.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.ui.validatePasswordConfirm

@Composable
fun PasswordConfirmTextField(
    password: String,
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val validationState =
        remember { derivedStateOf { validatePasswordConfirm(password, passwordConfirm) } }

    SignupTextField(
        value = passwordConfirm,
        onValueChange = onPasswordConfirmChange,
        label = stringResource(R.string.password_confirm),
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Password
        ),
        secure = true,
        validationState = validationState.value,
        modifier = modifier
    )
}
