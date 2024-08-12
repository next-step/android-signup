package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun SingUpButtonPreView() {
    SingUpButtonScreen(enabled = false)
}

@Composable
fun SingUpButtonScreen(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Sign Up",
            fontSize = 14.sp,
            color = Color(0xFFFFFFFF)
        )
    }
}
