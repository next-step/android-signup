package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R

@Composable
fun UsernameTextInputField(
    userName: String,
    onNameChange: (String) -> Unit,
) {

    val errorTextRes: Int? = remember(key1 = userName) {
        when {
            userName.isEmpty() -> null
            userName.length !in 2..5 -> {
                R.string.signup_error_name_length
            }

            !USERNAME_REGEX.toRegex().matches(userName) -> {
                R.string.signup_error_name_must_not_contain
            }

            else -> null
        }
    }

    SignUpTextInputField(
        value = userName,
        label = stringResource(id = R.string.signup_username),
        onValueChange = onNameChange,
        errorText = errorTextRes?.let { stringResource(id = it) }
    )

}


private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"


@Preview(showBackground = true)
@Composable
fun UsernameTextInputFieldPreview(
    @PreviewParameter(UsernameTextInputFieldPreviewParameterProvider::class) username: String
) {
    UsernameTextInputField(username, {})
}

internal class UsernameTextInputFieldPreviewParameterProvider :
    PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "",
        "세글자",
        "길이 초과 상태",
        "테스트12"
    )
}
