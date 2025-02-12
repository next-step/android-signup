package nextstep.signup.signup

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray30

@Composable
fun SignUpButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
            .width(296.dp)
            .height(50.dp),
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = Blue50,
            contentColor = Color.White,
            disabledContainerColor = Gray30,
            disabledContentColor = Gray30,
        ),
        content = {
            Text(
                text = stringResource(R.string.sign_up),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp
            )
        }
    )
}