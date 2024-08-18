package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun TitleText() {
    Text(
        text = stringResource(id = R.string.signup_title),
        style = TextStyle(
            fontSize = 26.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(700),
            color = Color.Black,
            letterSpacing = 0.26.sp
        ),
        modifier = Modifier
            .fillMaxWidth(1.0F)
            .padding(bottom = 42.dp),
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true)
@Composable
fun TitleTextPreview() {
    SignupTheme {
        TitleText()
    }
}