package nextstep.signup.ui.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.ui.ValidationState

@Composable
fun UsernameTextField(
    username: String,
    onUsernameChange: (String) -> Unit,
    validationState: ValidationState,
    modifier: Modifier = Modifier
) {
    SignupTextField(
        value = username,
        onValueChange = onUsernameChange,
        label = stringResource(R.string.username),
        validationState = validationState,
        modifier = modifier
    )
}
