package nextstep.signup.view

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.utils.SignUpValidator

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isValid by remember(value) {
        derivedStateOf { SignUpValidator.isValidEmail(value) }
    }

    SignUpBasicTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_email),
        value = value,
        errorMessage = if (isValid) "" else stringResource(R.string.signup_email_format_error),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}