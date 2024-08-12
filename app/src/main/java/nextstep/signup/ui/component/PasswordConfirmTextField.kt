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
        PasswordConfirmValidType.INVALID_EQUAL -> stringResource(id = R.string.password_confirm_equal_error)
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
private fun PasswordConfirmTextFieldPreview() {
    var passwordConfirm by remember { mutableStateOf("aa120001") }
    PasswordConfirmTextField(
        value = passwordConfirm,
        onValueChange = { passwordConfirm = it },
        passwordValue = "aa120000",
    )
}