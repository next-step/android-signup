package nextstep.signup.register.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.R


@Composable
fun SignUpTitle(
    modifier: Modifier = Modifier,
    title : String = stringResource(R.string.sign_up_title)
) {

    Text(
        modifier = modifier,
        text = title,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}


@Preview
@Composable
private fun PreviewSignUpTitle() {
    SignUpTitle()
}