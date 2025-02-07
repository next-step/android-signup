package nextstep.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
internal fun SignupTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = Color.Black,
        lineHeight = 20.sp,
        style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (0.01).em,
        ),
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupTitlePreview() {
    SignupTitle(stringResource(R.string.signup_title))
}
