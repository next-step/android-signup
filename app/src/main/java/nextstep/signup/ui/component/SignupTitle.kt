package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun SignupTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.signup_title),
        fontSize = 26.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.01.em,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
    )
}

@Preview
@Composable
fun SignupTitlePreview(modifier: Modifier = Modifier) {
    SignupTitle()
}