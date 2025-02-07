package nextstep.signup.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun SignUpButtonPreview() {
    SignupTheme {
        SignUpButton(
            text = "Sign Up",
            onClick = {},
        )
    }
}
