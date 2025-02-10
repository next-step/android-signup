package nextstep.signup.userregister.widget

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.component.SubmitButton

@Composable
fun RegisterButton(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SubmitButton(
        text = "Sign Up",
        onClickButton = onButtonClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun RegisterButtonPreview() {
    RegisterButton(
        onButtonClick = {},
        modifier = Modifier.width(296.dp)
    )
}
