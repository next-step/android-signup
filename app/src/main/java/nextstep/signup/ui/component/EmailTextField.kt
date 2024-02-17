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


@Preview
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    email: String = "",
    onValueChange: (String) -> Unit = {}
) {
    val supportingText: @Composable (() -> Unit)? = when (EmailError.checkBy(email = email)) {
        EmailError.NOT_MATCH_FORMAT -> {
            { Text(text = stringResource(R.string.email_format_error)) }
        }
        EmailError.NONE -> {
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

enum class EmailError {
    NOT_MATCH_FORMAT,
    NONE;

    companion object {
        private val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()

        fun checkBy(email: String): EmailError {
            return when {
                email.isEmpty() -> {
                    NONE
                }
                EMAIL_REGEX.matches(email)
                    .not() -> {
                        NOT_MATCH_FORMAT
                    }
                else -> {
                    NONE
                }
            }
        }
    }
}
