package nextstep.signup.component.title

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R

/**
 * 회원가입 제목용 composeable 함수
 **/
@Composable
fun SignUpTitle(
	modifier: Modifier,
) {
	Text(
		text = stringResource(id = R.string.signe_up_title), modifier = modifier, style = TextStyle(
			fontSize = 26.sp,
			fontFamily = FontFamily.SansSerif,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center
		)
	)
}

/**
 * 회원가입 제목용 composeable 함수 미리보기
 * (@preview 사용위해 parameter없는 composable 생성)
 **/
@Preview(
	backgroundColor = 0xFFFFFFFFL,
	showBackground = true,
)
@Composable
fun PreviewSignUpTitle() {
	SignUpTitle(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 70.dp)
	)
}
