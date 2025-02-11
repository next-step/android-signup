package nextstep.signup.signup.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignUpButton(modifier: Modifier = Modifier, buttonTitle: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(buttonTitle)
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignUpButton(buttonTitle = "Sign Up", onClick = {})
}
