package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.component.PasswordConfirmValidationResult.Companion.validatePasswordConfirm

@Composable
fun PasswordConfirmTextField(
    password: String,
    confirmPassword: String,
    onPasswordChange: (String) -> Unit,
    onValidationStateChanged: (Boolean) -> Unit
) {

    val validationResult = validatePasswordConfirm(password, confirmPassword)
    val errorMessage = when (validationResult) {
        PasswordConfirmValidationResult.Empty -> ""
        PasswordConfirmValidationResult.Mismatch -> stringResource(id = R.string.sign_up_password_mismatch_error)
        else -> ""
    }

    LaunchedEffect(key1 = confirmPassword, key2 = errorMessage) {
        onValidationStateChanged(confirmPassword.isNotBlank() && errorMessage.isBlank())
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

enum class PasswordConfirmValidationResult {
    Valid,
    Empty,
    Mismatch;

    companion object {
        fun validatePasswordConfirm(password: String?, confirmPassword: String?): PasswordConfirmValidationResult {
            return when {
                password.isNullOrEmpty() || confirmPassword.isNullOrEmpty() -> Empty
                password != confirmPassword -> Mismatch
                else -> Valid
            }
        }
    }
}

class PasswordConfirmPreviewParameterProvider : PreviewParameterProvider<Pair<String, String>> {
    override val values = sequenceOf(
        "12345678ab" to "12345678ab",
        "12345678ab" to "12345678ba"
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview(
    @PreviewParameter(PasswordConfirmPreviewParameterProvider::class) passwordPair: Pair<String, String>
) {
    PasswordConfirmTextField(
        password = passwordPair.first,
        confirmPassword = passwordPair.second,
        {}, {}
    )
}
