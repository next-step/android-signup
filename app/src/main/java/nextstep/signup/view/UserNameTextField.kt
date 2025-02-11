package nextstep.signup.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.signup.R
import nextstep.signup.utils.SignUpErrorType

@Composable
fun UserNameTextField(
    userName: String,
    errorType: SignUpErrorType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SignUpBasicTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_username),
        value = userName,
        errorType = errorType,
        onValueChange = {
            onValueChange(it)
        }
    )
}