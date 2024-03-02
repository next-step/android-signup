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
fun PasswordConfirmInputField(
    password: String,
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit,
) {

    val errorTextRes: Int? = remember(key1 = password, key2 = passwordConfirm) {
        when {
            passwordConfirm.isEmpty() -> null
            passwordConfirm != password -> {
                R.string.signup_error_password_not_matched
            }

            else -> null
        }
    }

    SignUpTextInputField(
        value = passwordConfirm,
        label = stringResource(id = R.string.signup_password_confirm),
        onValueChange = onPasswordConfirmChange,
        errorText = errorTextRes?.let { stringResource(id = it) },
        visualTransformation = PasswordVisualTransformation()
    )

}

@Preview(showBackground = true)
@Composable
fun PasswordConfirmInputFieldPreview(
    @PreviewParameter(PasswordConfirmInputFieldPreviewParameterProvider::class) passwords: Pair<String, String>
) {
    PasswordConfirmInputField(
        password = passwords.first,
        passwordConfirm = passwords.second,
        onPasswordConfirmChange = {})
}

internal class PasswordConfirmInputFieldPreviewParameterProvider :
    PreviewParameterProvider<Pair<String, String>> {
    override val values = sequenceOf(
        "" to "",
        "" to "password",
        "password" to "password",
    )
}
