package nextstep.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50

@Composable
internal fun SignupButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            )
        )
    }
}

@Preview
@Composable
private fun SignupButtonPreview() {
    SignupButton(
        text = "Sign Up",
        onClick = {}
    )
}
