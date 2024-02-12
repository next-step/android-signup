package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
internal fun UsernameTextField(
    username: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = remember(username) {
        when {
            username.isEmpty() -> null
            username.length !in (2..5) -> {
                { Text(text = stringResource(id = R.string.signup_username_length_error)) }
            }
            !USERNAME_REGEX.toRegex().matches(username) -> {
                { Text(text = stringResource(id = R.string.signup_username_hangeul_error)) }
            }
            else -> null
        }
    }

    TextField(
        value = username,
        onValueChange = onNameChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.signup_username_placeholder)) },
        singleLine = true,
        supportingText = supportingText,
        isError = supportingText != null,
    )
}

private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"

private class UsernameTextFieldPreviewParameter : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("", "김컴포즈", "김", "12345")
}

@Preview(showBackground = true)
@Composable
private fun UsernameTextFieldPreview(@PreviewParameter(UsernameTextFieldPreviewParameter::class) username: String) {
    MaterialTheme {
        UsernameTextField(
            username = username,
            onNameChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
