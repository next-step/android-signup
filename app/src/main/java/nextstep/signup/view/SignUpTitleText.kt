package nextstep.signup.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.view.ui.theme.Dimens.TextTitle
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun SignUpTitleText() {
    Text(
        text = stringResource(id = R.string.welcome),
        textAlign = TextAlign.Center,
        fontSize = TextTitle,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignupTheme {
        SignUpTitleText()
    }
}