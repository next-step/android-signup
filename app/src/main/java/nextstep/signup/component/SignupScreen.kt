package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R


@Composable
internal fun SignupScreen(
    modifier: Modifier = Modifier,
) {
    var isValidation by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SignupTitle(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(R.string.signup_title)
        )
        SignupInputFields(
            modifier = Modifier.padding(top = 20.dp),
            onValidation = {
                isValidation = it
            }
        )
        SignupButton(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(R.string.signup_button),
            onClick = {},
            enabled = isValidation
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    SignupScreen()
}
