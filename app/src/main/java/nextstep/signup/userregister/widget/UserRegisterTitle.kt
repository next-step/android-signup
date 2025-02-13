package nextstep.signup.userregister.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.component.Title

@Composable
fun UserRegisterTitle(modifier: Modifier = Modifier) {
    Title(stringResource(R.string.user_register_screen_title), modifier)
}

@Preview
@Composable
private fun UserRegisterTitlePreview() {
    UserRegisterTitle(modifier = Modifier)
}
