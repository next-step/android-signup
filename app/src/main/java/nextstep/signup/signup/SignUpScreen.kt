package nextstep.signup.signup

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.signup.component.SignUpContents
import nextstep.signup.signup.component.SignUpTitle
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            SignUpTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 42.dp),
                title = stringResource(R.string.signup_main_title)
            )
        },
    ) { contentPadding ->
        SignUpContents(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 32.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}
