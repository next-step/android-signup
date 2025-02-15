package nextstep.signup.ui.screen.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.component.SignupTextField

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
        errorMessage = when (validationState) {
            is ValidationState.Error -> stringResource(validationState.resourceId)
            ValidationState.Success -> null
        },
        modifier = modifier
    )
}
