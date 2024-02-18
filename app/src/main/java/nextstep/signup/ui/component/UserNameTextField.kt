package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R

@Composable
fun UserNameTextField(
    modifier: Modifier = Modifier,
    userName: String,
    onValueChange: (String) -> Unit
) {
    val supportingText: @Composable (() -> Unit)? =
        when (UserNameError.checkBy(userName = userName)) {
            UserNameError.OUT_OF_RANGE_LENGTH -> {
                { Text(text = stringResource(R.string.username_length_error)) }
            }
            UserNameError.CANNOT_CONTAIN_NUMBERS_OR_SYMBOLS -> {
                { Text(text = stringResource(R.string.username_cannot_contain_numbers_or_symbols)) }
            }
            UserNameError.NONE -> {
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

@Preview
@Composable
private fun Preview() {
    UserNameTextField(
        modifier = Modifier,
        userName = "",
        onValueChange = {}
    )
}

enum class UserNameError {
    OUT_OF_RANGE_LENGTH,
    CANNOT_CONTAIN_NUMBERS_OR_SYMBOLS,
    NONE;

    val isPass: Boolean
        get() = this == NONE

    companion object {
        private val VALID_RANGE = 2..5
        private val USERNAME_REGEX = "^[a-zA-Z가-힣]+$".toRegex()

        fun checkBy(userName: String): UserNameError {
            return when {
                userName.isEmpty() -> {
                    NONE
                }
                userName.length !in VALID_RANGE -> {
                    OUT_OF_RANGE_LENGTH
                }
                USERNAME_REGEX.matches(userName)
                    .not() -> {
                    CANNOT_CONTAIN_NUMBERS_OR_SYMBOLS
                }
                else -> {
                    NONE
                }
            }
        }
    }
}
