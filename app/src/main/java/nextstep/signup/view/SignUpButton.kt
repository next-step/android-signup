package nextstep.signup.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.view.ui.theme.Blue50
import nextstep.signup.view.ui.theme.Dimens.ButtonHeight
import nextstep.signup.view.ui.theme.Dimens.ButtonRadius
import nextstep.signup.view.ui.theme.Dimens.ButtonWidth
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun SignUpButton(enabled: Boolean = true) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(ButtonHeight)
            .width(ButtonWidth)
            .clip(RoundedCornerShape(ButtonRadius)),
        colors = ButtonDefaults.buttonColors(Blue50),
        enabled = enabled,
    ) {
        Text(text = stringResource(id = R.string.sign_up))
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignupTheme {
        SignUpButton()
    }
}