package nextstep.signup.ui.component.textfield

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.validateEmail

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    validationState: ValidationState,
    modifier: Modifier = Modifier
) {
    SignupTextField(
        value = email,
        onValueChange = onEmailChange,
        label = stringResource(R.string.email),
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Email
        ),
        validationState = validationState,
        modifier = modifier
    )
}
