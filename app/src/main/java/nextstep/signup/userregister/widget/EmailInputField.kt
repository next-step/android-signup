package nextstep.signup.userregister.widget

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.component.UserRegisterTextField

@Composable
fun EmailInputField(
    value: String,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UserRegisterTextField(
        value = value,
        errorMessage = errorMessage,
        onValueChange = onValueChange,
        labelText = stringResource(R.string.user_register_input_email_label),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun EmailInputFieldPreview() {
    EmailInputField(
        value = "",
        errorMessage = "",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun EmailInputFieldWithValuePreview() {
    EmailInputField(
        value = "fbghgus123@naver.com",
        errorMessage = "",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}

@Preview
@Composable
private fun EmailInputFieldWithErrorPreview() {
    EmailInputField(
        value = "fbghgus123@naver.com",
        errorMessage = "에러",
        onValueChange = {},
        modifier = Modifier.width(296.dp)
    )
}
