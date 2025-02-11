package nextstep.signup.view

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.R
import nextstep.signup.utils.SignUpErrorType

@Composable
fun PasswordConfirmTextField(
    value: String,
    errorType: SignUpErrorType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    SignUpBasicTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_confirm_password),
        value = value,
        errorType = errorType,
        onValueChange = onValueChange,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}