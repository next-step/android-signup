package nextstep.signup.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

private enum class UsernameError {
    NONE, LENGTH_ERROR, INVALID_CHARACTER_ERROR
}

@Composable
fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val errorType by remember(value) {
        derivedStateOf {
            when {
                value.isEmpty() -> UsernameError.NONE
                !value.matches(Regex(RegexConst.USERNAME_REGEX)) -> UsernameError.INVALID_CHARACTER_ERROR
                value.length !in 2..5 -> UsernameError.LENGTH_ERROR
                else -> UsernameError.NONE
            }
        }
    }

    SignUpTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.sign_up_user_name),
        isError = errorType != UsernameError.NONE,
        supportingText = when (errorType) {
            UsernameError.LENGTH_ERROR -> stringResource(R.string.sign_up_user_name_length_error)
            UsernameError.INVALID_CHARACTER_ERROR -> stringResource(R.string.sign_up_user_name_invalid_character_error)
            UsernameError.NONE -> ""
        }
    )
}

private class UsernameTextFieldPreviewParameter: PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("양수진", "양수진1234", "z1존수진", "yangsooplus", "")

}

@Preview(showBackground = true)
@Composable
private fun UsernameTextFieldPreview(@PreviewParameter(UsernameTextFieldPreviewParameter::class) value: String) {
    SignupTheme {
        UsernameTextField(
            value = value,
            onValueChange = {}
        )
    }
}