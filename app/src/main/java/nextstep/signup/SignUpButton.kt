package nextstep.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    Button(
        modifier = modifier.fillMaxWidth().height(50.dp),
        onClick = onClick,
        content = {
            Text(text = text, style = textStyle)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50
        )
    )
}

@Preview
@Composable
private fun SignUpButtonPreview() {
    SignupTheme {
        SignUpButton(
            text = "Sign Up",
            onClick = {}
        )
    }
}