package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R

@Composable
fun PasswordInputField(
    password: String,
    onPasswordChange: (String) -> Unit,
) {

    val errorTextRes: Int? = remember(key1 = password) {
        when {
            password.isEmpty() -> null
            password.length !in 8..16 -> {
                R.string.signup_error_password_length
            }

            !PASSWORD_REGEX.toRegex().matches(password) -> {
                R.string.signup_error_password_must_contain_text
            }

            else -> null
        }
    }

    SignUpTextInputField(
        value = password,
        label = stringResource(id = R.string.signup_password),
        onValueChange = onPasswordChange,
        errorText = errorTextRes?.let { stringResource(id = it) },
        visualTransformation = PasswordVisualTransformation()
    )

}

private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

@Preview(showBackground = true)
@Composable
fun PasswordInputFieldPreview(
    @PreviewParameter(PasswordInputFieldPreviewParameterProvider::class) password: String
) {
    PasswordInputField(password, onPasswordChange = {})
}

internal class PasswordInputFieldPreviewParameterProvider :
    PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "",
        "englishAnd123",
        "123",
        "16자보다 큰 비밀번호 테스트 입니다. 비밀번호 몇자리",
        "면",
    )
}
