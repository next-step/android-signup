package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(title: String, modifier: Modifier) {
    Text(
        title,
        style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 20.sp,
        ),
        modifier = modifier,
    )
}