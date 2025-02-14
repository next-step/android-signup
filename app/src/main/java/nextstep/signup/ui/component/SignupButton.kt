package nextstep.signup.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupButton(
    label: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        content = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        },
        colors = ButtonDefaults
            .buttonColors()
            .copy(containerColor = Blue50),
    )
}

@Preview
@Composable
private fun SignupButtonPreview() {
    SignupTheme {
        SignupButton(
            label = "text",
            enabled = true,
            onClick = {}
        )
    }
}
