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
fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    isValidationError: Boolean,
    modifier: Modifier = Modifier
) {
    SignUpInput(
        value = value,
        onValueChange = onValueChange,
        isError = isValidationError,
        supportingText = {
            if (isValidationError) {
                Text(text = stringResource(id = R.string.passwordValidationMessage))
            }
        },
        label = stringResource(id = R.string.passwordLabel),
        modifier = modifier
    )
}

@Preview
@Composable
private fun PasswordInputPreview() {
    SignupTheme {
        PasswordInput(
            value = "1234",
            onValueChange = { },
            isValidationError = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}