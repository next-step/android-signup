package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.R
import nextstep.signup.core.validation.PasswordMatchValidationResult

@Composable
internal fun PasswordConfirmTextField(
    passwordConfirmValue: String,
    passwordMatchValidationResult: PasswordMatchValidationResult,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hasSupportingText = remember(passwordConfirmValue, passwordMatchValidationResult) {
        passwordConfirmValue.isNotEmpty() && passwordMatchValidationResult != PasswordMatchValidationResult.VALID
    }

    val supportingText: @Composable (() -> Unit)? = if (hasSupportingText) {
        when (passwordMatchValidationResult) {
            PasswordMatchValidationResult.VALID -> null
            PasswordMatchValidationResult.MISMATCH -> {
                { Text(text = stringResource(id = R.string.signup_password_mismatch_error)) }
            }
        }
    } else null

    SignUpTextField(
        modifier = modifier.testTag("password_confirm"),
        value = passwordConfirmValue,
        onValueChange = onPasswordConfirmChange,
        label = { Text(text = stringResource(id = R.string.signup_password_confirm)) },
        visualTransformation = PasswordVisualTransformation(),
        supportingText = supportingText
    )
}
