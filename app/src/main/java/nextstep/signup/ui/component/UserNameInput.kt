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
fun UserNameInput(
    value: String,
    onValueChange: (String) -> Unit,
    isLengthError: Boolean,
    isFormatError: Boolean,
    modifier: Modifier = Modifier
) {
    SignUpInput(
        value = value,
        onValueChange = onValueChange,
        isError = isLengthError || isFormatError,
        supportingText = {
            if (isLengthError) {
                Text(text = stringResource(id = R.string.userNameLengthValidationMessage))
            } else if (isFormatError) {
                Text(text = stringResource(id = R.string.userNameInvalidCharactersMessage))
            }
        },
        label = stringResource(id = R.string.userNameLabel),
        modifier = modifier
    )
}

@Preview
@Composable
private fun UserNameInputPreview() {
    SignupTheme {
        UserNameInput(
            value = "asdff",
            onValueChange = {  },
            isLengthError = true,
            isFormatError = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}