package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.core.validation.PasswordValidationResult
import nextstep.signup.ui.ThemePreviews

@Composable
internal fun PasswordTextField(
    password: String,
    passwordValidationResult: PasswordValidationResult,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hasSupportingText = remember(password, passwordValidationResult) {
        password.isNotEmpty() && passwordValidationResult != PasswordValidationResult.VALID
    }

    val supportingText: @Composable (() -> Unit)? = if (hasSupportingText) {
        when (passwordValidationResult) {
            PasswordValidationResult.VALID -> null
            PasswordValidationResult.INVALID_LENGTH -> {
                { Text(text = stringResource(id = R.string.signup_password_length_error)) }
            }
            PasswordValidationResult.INVALID_COMPLEXITY -> {
                { Text(text = stringResource(id = R.string.signup_password_complexity_error)) }
            }
        }
    } else null

    SignUpTextField(
        modifier = modifier.testTag("password"),
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = stringResource(id = R.string.signup_password)) },
        supportingText = supportingText,
    )
}

private class PasswordTextFieldPreviewParameterProvider : PreviewParameterProvider<Pair<String, PasswordValidationResult>> {
    override val values = sequenceOf(
        "" to PasswordValidationResult.VALID,
        "short" to PasswordValidationResult.INVALID_LENGTH,
        "simple123" to PasswordValidationResult.INVALID_COMPLEXITY,
        "Complex@123" to PasswordValidationResult.VALID
    )
}

@ThemePreviews
@Composable
private fun PreviewPasswordTextField(
    @PreviewParameter(PasswordTextFieldPreviewParameterProvider::class) passwordData: Pair<String, PasswordValidationResult>
) {
    PasswordTextField(
        password = passwordData.first,
        passwordValidationResult = passwordData.second,
        onPasswordChange = {},
        modifier = Modifier
    )
}
