package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FullButton(
    isEnabled: Boolean,
    text: String,
    textColor: Color,
    containerColor: Color,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onButtonClick.invoke() },
        modifier = modifier.height(50.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
        )
    }
}
