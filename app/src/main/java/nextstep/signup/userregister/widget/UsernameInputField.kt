package nextstep.signup.userregister.widget

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.component.UserRegisterTextField
import nextstep.signup.userregister.validation.InputValueValidator

@Composable
fun UsernameInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = value,
        validationResult = InputValueValidator.UserName.checkValue(value),
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_username_label),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun UsernameInputFieldPreview() {
    UsernameInputField(
        value = "",
        onValueChange = {},
        modifier = Modifier.width(296.dp),
    )
}

@Preview
@Composable
private fun UsernameInputFieldWithValuePreview() {
    UsernameInputField(
        value = "abcde123",
        onValueChange = {},
        modifier = Modifier.width(296.dp),
    )
}

@Preview
@Composable
private fun UsernameInputFieldWithErrorPreview() {
    UsernameInputField(
        value = "abcde123",
        onValueChange = {},
        modifier = Modifier.width(296.dp),
    )
}
