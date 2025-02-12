package nextstep.signup.ui.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.ui.validateUsername

@Composable
fun UsernameTextField(
    username: String,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val validationState = remember { derivedStateOf { validateUsername(username) } }

    SignupTextField(
        value = username,
        onValueChange = onUsernameChange,
        label = stringResource(R.string.username),
        validationState = validationState.value,
        modifier = modifier
    )
}
