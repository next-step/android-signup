package nextstep.signup.ui.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.ui.validateEmail

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val validationState = remember { derivedStateOf { validateEmail(email) } }

    SignupTextField(
        value = email,
        onValueChange = onEmailChange,
        label = stringResource(R.string.email),
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Email
        ),
        validationState = validationState.value,
        modifier = modifier
    )
}
