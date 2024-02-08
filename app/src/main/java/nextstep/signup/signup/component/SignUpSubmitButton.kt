package nextstep.signup.signup.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50

@Composable
fun SignUpSubmitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50,
        ),
        contentPadding = PaddingValues(
            vertical = 15.dp,
        )
    ) {
        Text(
            text = "Sign Up",
            color = Color.White,
            fontSize = 14.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpSubmitButtonPreview() {
    SignUpSubmitButton(
        onClick = {},
    )
}
