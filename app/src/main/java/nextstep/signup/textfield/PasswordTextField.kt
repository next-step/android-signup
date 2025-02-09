package nextstep.signup.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

private enum class PasswordError {
    NONE, LENGTH_ERROR, REQUIRED_CHARACTER_NOT_INCLUDE_ERROR
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val errorType by remember(value) {
        derivedStateOf {
            when {
                value.isEmpty() -> PasswordError.NONE
                value.length !in 8..16 -> PasswordError.LENGTH_ERROR
                !value.matches(Regex(RegexConst.PASSWORD_REGEX)) -> PasswordError.REQUIRED_CHARACTER_NOT_INCLUDE_ERROR
                else -> PasswordError.NONE
            }
        }
    }

    SignUpTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.sign_up_password),
        isError = errorType != PasswordError.NONE,
        supportingText = when (errorType) {
            PasswordError.NONE -> ""
            PasswordError.LENGTH_ERROR -> stringResource(R.string.sign_up_password_length_error)
            PasswordError.REQUIRED_CHARACTER_NOT_INCLUDE_ERROR -> stringResource(R.string.sign_up_password_required_character_not_include_error)
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

private class PasswordTextFieldPreviewParameter: PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("qwer", "qwer123456", "이건비밀번호입니다", "qwer1234!@#$", "")

}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview(@PreviewParameter(PasswordTextFieldPreviewParameter::class) value: String) {
    SignupTheme {
        Column {
            Text(text = "Password: $value")
            PasswordTextField(
                value = value,
                onValueChange = {}
            )
        }
    }
}