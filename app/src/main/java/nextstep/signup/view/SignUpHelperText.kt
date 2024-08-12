package nextstep.signup.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.view.ui.theme.Dimens.TextHelper
import nextstep.signup.view.ui.theme.Red50
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun SignUpHelperText(message: String) {
    if (message.isNotEmpty()) {
        Text(
            text = message,
            fontSize = TextHelper,
            color = Red50,
            textAlign = TextAlign.Start,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpHelperTextPreview() {
    SignupTheme {
        SignUpHelperText(stringResource(id = R.string.password_do_not_match))
    }
}