package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun PasswordConfirmInput(
    value: String,
    onValueChange: (String) -> Unit,
    isMismatchError: Boolean,
    modifier: Modifier = Modifier
) {
    SignUpInput(
        value = value,
        onValueChange = onValueChange,
        isError = isMismatchError,
        supportingText = {
            if (isMismatchError) {
                Text(text = stringResource(id = R.string.passwordMismatchMessage))
            }
        },
        label = stringResource(id = R.string.passwordConfirmLabel),
        modifier = modifier
    )
}

@Preview
@Composable
private fun PasswordConfirmInputPreview() {
    SignupTheme {
        PasswordConfirmInput(
            value = "asd12344",
            onValueChange = { },
            isMismatchError = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}