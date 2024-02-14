package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R

private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"

@Preview
@Composable
fun UserNameTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    userName: String = ""
) {
    val supportingText: @Composable (() -> Unit)? = when {
        userName.isEmpty() -> {
            null
        }

        userName.length !in 2..5 -> {
            {
                Text(text = stringResource(R.string.username_length_error))
            }
        }

        USERNAME_REGEX.toRegex()
            .matches(userName)
            .not() -> {
            {
                Text(text = stringResource(R.string.username_cannot_contain_numbers_or_symbols))
            }
        }

        else -> {
            null
        }
    }
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = userName,
        onValueChange = { value ->
            onValueChange(value)
        },
        label = {
            Text(text = stringResource(R.string.username))
        },
        supportingText = supportingText,
        isError = supportingText != null
    )
}
