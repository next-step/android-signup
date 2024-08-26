package nextstep.signup.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignUpDisableBtnContainerColor
import nextstep.signup.ui.theme.SignUpDisableBtnContentColor

/**
 * 회원가입 버튼 composeable 함수
 **/
@Composable
fun SignUpButton(
	modifier: Modifier,
	onClick: () -> Unit,
	isBtnEnable: Boolean
) {
	Button(
		colors = ButtonDefaults
			.buttonColors(
				containerColor = Blue50,
				contentColor = Color.White,
				disabledContentColor = SignUpDisableBtnContentColor,
				disabledContainerColor = SignUpDisableBtnContainerColor
			),
		modifier = modifier,
		onClick = { onClick() },
		enabled = isBtnEnable,
	) {
		Text(text = stringResource(id = R.string.sign_up))
	}
}

/**
 * 회원가입 버튼 composeable 함수 미리보기
 **/
@Preview
@Composable
fun PreviewSignUpButton() {
	SignUpButton(
		modifier = Modifier
			.fillMaxWidth()
			.heightIn(min = 115.dp)
			.padding(30.dp),
		isBtnEnable = true,
		onClick = {
			// 회원가입 버튼 클릭시 로직
		}
	)
}
