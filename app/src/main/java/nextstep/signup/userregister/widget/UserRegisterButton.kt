package nextstep.signup.userregister.widget

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.UserRegisterState
import nextstep.signup.component.SubmitButton
import nextstep.signup.userregister.validation.UserRegisterButtonValidator

@Composable
fun UserRegisterButton(
    state: UserRegisterState,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SubmitButton(
        text = "Sign Up",
        enabled = UserRegisterButtonValidator.checkValidation(state),
        onClickButton = onButtonClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun UserRegisterButtonPreview() {
    UserRegisterButton(
        state = UserRegisterState(),
        onButtonClick = {},
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun UserRegisterButtonDisabledPreview() {
    UserRegisterButton(
        state = UserRegisterState(),
        onButtonClick = {},
        modifier = Modifier.width(296.dp)
    )
}

