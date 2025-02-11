package nextstep.signup.view

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.utils.SignUpErrorType

@Composable
fun EmailTextField(
    value: String,
    errorType: SignUpErrorType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SignUpBasicTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_email),
        value = value,
        errorType = errorType,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}