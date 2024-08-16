package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.domain.PasswordConfirmValidType
import nextstep.signup.domain.PasswordConfirmValidator

@Composable
fun PasswordConfirmTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    passwordValue: String,
) {
    val supportingTextValue = if (value.isEmpty()) null
    else when (PasswordConfirmValidator.match(passwordValue, value)) {
        PasswordConfirmValidType.VALID -> null
        PasswordConfirmValidType.INVALID_NOT_EQUAL -> stringResource(id = R.string.password_confirm_equal_error)
    }

    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        labelValue = stringResource(id = R.string.password_confirm),
        visualTransformation = PasswordVisualTransformation(),
        supportingTextValue = supportingTextValue,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun PasswordConfirmTextFieldPreview(
    @PreviewParameter(
        PasswordConfirmTextFieldPreviewParameterProvider::class
    ) param: PasswordConfirmTextFieldPreviewParameter
) {
    var passwordConfirm by remember { mutableStateOf(param.passwordConfirm) }
    PasswordConfirmTextField(
        value = passwordConfirm,
        onValueChange = { passwordConfirm = it },
        passwordValue = param.password,
    )
}

private class PasswordConfirmTextFieldPreviewParameterProvider :
    PreviewParameterProvider<PasswordConfirmTextFieldPreviewParameter> {
    override val values: Sequence<PasswordConfirmTextFieldPreviewParameter> = sequenceOf(
        PasswordConfirmTextFieldPreviewParameter("aa120000", "aa120000"),
        PasswordConfirmTextFieldPreviewParameter("aa120000", ""),
        PasswordConfirmTextFieldPreviewParameter("aa120000", "aa120001"),
    )
}

private data class PasswordConfirmTextFieldPreviewParameter(
    val password: String,
    val passwordConfirm: String,
)