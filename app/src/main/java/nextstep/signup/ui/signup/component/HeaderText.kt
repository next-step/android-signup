package nextstep.signup.ui.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.RobotoBold

@Composable
fun HeaderText() {
    Text(
        text = stringResource(R.string.signup_header_title),
        color = Color.Black,
        fontSize = 26.sp,
        fontFamily = RobotoBold,
    )
}

@Preview
@Composable
private fun HeaderTextPreview() {
    HeaderText()
}
