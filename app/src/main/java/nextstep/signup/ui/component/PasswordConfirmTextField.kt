package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
fun PasswordConfirmTextField(
    confirmPassword: String,
    onPasswordChange: (String) -> Unit,
    validationResult: ConfirmPasswordValidationResult
) {

    val errorMessage = when (validationResult) {
        ConfirmPasswordValidationResult.Empty -> ""
        ConfirmPasswordValidationResult.Mismatch -> stringResource(id = R.string.sign_up_password_mismatch_error)
        else -> ""
    }

    InputTextField(
        modifier = Modifier
            .testTag("passwordConfirm")
            .padding(top = 6.dp),
        value = confirmPassword,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.sign_up_label_password_confirm),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = errorMessage
    )
}

enum class ConfirmPasswordValidationResult {
    Valid,
    Empty,
    Mismatch;

    companion object {
        fun validateConfirmPassword(
            password: String?,
            confirmPassword: String?
        ): ConfirmPasswordValidationResult {
            return when {
                password.isNullOrEmpty() || confirmPassword.isNullOrEmpty() -> Empty
                password != confirmPassword -> Mismatch
                else -> Valid
            }
        }
    }
}

class PasswordConfirmPreviewParameterProvider :
    PreviewParameterProvider<Pair<String, ConfirmPasswordValidationResult>> {
    override val values = sequenceOf(
        "12345678ab" to ConfirmPasswordValidationResult.Valid,
        "12345678ab" to ConfirmPasswordValidationResult.Mismatch
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview(
    @PreviewParameter(PasswordConfirmPreviewParameterProvider::class)
    parameter: Pair<String, ConfirmPasswordValidationResult>,
) {
    PasswordConfirmTextField(
        confirmPassword = parameter.first,
        onPasswordChange = {},
        validationResult = parameter.second
    )
}
