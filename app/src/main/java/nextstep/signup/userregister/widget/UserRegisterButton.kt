package nextstep.signup.userregister.widget

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.component.SubmitButton

@Composable
fun UserRegisterButton(
    enabled: Boolean,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SubmitButton(
        text = "Sign Up",
        enabled = enabled,
        onClickButton = onButtonClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun UserRegisterButtonPreview() {
    UserRegisterButton(
        onButtonClick = {},
        enabled = true,
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun UserRegisterButtonDisabledPreview() {
    UserRegisterButton(
        onButtonClick = {},
        enabled = false,
        modifier = Modifier.width(296.dp)
    )
}

