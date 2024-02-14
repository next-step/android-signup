package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R

private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

@Preview
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    email: String = "",
    onValueChange: (String) -> Unit = {}
) {
    val supportingText: @Composable (() -> Unit)? = when {
        email.isEmpty() -> {
            null
        }

        EMAIL_REGEX.toRegex()
            .matches(email)
            .not() -> {
            {
                Text(text = stringResource(R.string.email_format_error))
            }
        }

        else -> {
            null
        }
    }
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = email,
        onValueChange = { value ->
            onValueChange(value)
        },
        label = {
            Text(text = stringResource(R.string.email))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        supportingText = supportingText,
        isError = supportingText != null
    )
}
