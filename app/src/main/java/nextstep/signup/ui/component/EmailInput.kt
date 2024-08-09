package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

@Composable
fun EmailInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val errorMessageResId = getEmailErrorMessage(value)

    TextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        isError = errorMessageResId != null,
        supportingText = {
            if (errorMessageResId != null) {
                Text(text = stringResource(id = errorMessageResId))
            }
        },
        label = { Text(text = stringResource(id = R.string.emailLabel)) },
        modifier = modifier
    )
}

fun getEmailErrorMessage(value: String): Int? {
    return when {
        value.isEmpty() -> null
        !value.matches(Regex(EMAIL_REGEX)) -> R.string.emailInvalidFormatMessage
        else -> null
    }
}

@Preview
@Composable
private fun EmailInputPreview() {
    var email by remember { mutableStateOf("") }

    SignupTheme {
        EmailInput(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}