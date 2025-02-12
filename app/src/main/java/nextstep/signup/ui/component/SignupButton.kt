package nextstep.signup.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.BlackAlpha12
import nextstep.signup.ui.theme.BlackDark
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.White


@Composable
fun SignupButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50,
            contentColor = White,
            disabledContainerColor = BlackAlpha12,
            disabledContentColor = BlackDark
        ),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 15.dp
        )
    ) {
        Text(
            text = text,
            modifier = if (enabled) modifier else modifier.alpha(0.38f),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(name = "enable")
@Composable
fun SignupButtonEnablePreview() {
    SignupButton(
        text = "Sign Up",
        onClick = { }
    )
}

@Preview(name = "disabled", showBackground = true)
@Composable
fun SignupButtonDisablePreview() {
    SignupButton(
        text = "Sign Up",
        onClick = { },
        enabled = false
    )
}