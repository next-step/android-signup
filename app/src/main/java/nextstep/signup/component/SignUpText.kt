package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun SignUpTitleText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 26.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
        ),
    )
}


@Preview
@Composable
private fun PreviewSignUpTitleText() {
    SignUpTitleText(text = stringResource(R.string.sign_up_text_title))
}

