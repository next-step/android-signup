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
import nextstep.signup.domain.PasswordValidType
import nextstep.signup.domain.PasswordValidator

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val supportingTextValue = if (value.isEmpty()) null
    else when (PasswordValidator.match(value)) {
        PasswordValidType.VALID -> null
        PasswordValidType.INVALID_LENGTH -> stringResource(id = R.string.password_length_error)
        PasswordValidType.INVALID_REGEX -> stringResource(id = R.string.password_regex_error)
    }

    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        labelValue = stringResource(id = R.string.password),
        visualTransformation = PasswordVisualTransformation(),
        supportingTextValue = supportingTextValue,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun PasswordTextFieldPreview(
    @PreviewParameter(PasswordTextFieldPreviewParameterProvider::class) param: String,
) {
    var password by remember { mutableStateOf(param) }
    PasswordTextField(value = password, onValueChange = { password = it })
}

private class PasswordTextFieldPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "aa120000",
        "",
        "aa12000",
        "aa120000aa120000a",
        "11111111",
        "abcdefgh"
    )
}