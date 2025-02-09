package nextstep.signup.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme


@Composable
fun PasswordConfirmTextField(
    value: String,
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isNotMatched by remember(value, password) { derivedStateOf { value.isNotEmpty() && value != password } }

    SignUpTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.sign_up_password_confirm),
        isError = isNotMatched,
        supportingText = if (isNotMatched) stringResource(R.string.sign_up_password_confirm_not_matched_error) else "",
        visualTransformation = PasswordVisualTransformation()
    )
}



@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview() {
    SignupTheme {
        val password = "qwer1234!@#$"
        Column {
            PasswordConfirmTextField(
                value = password,
                password = password,
                onValueChange = {}
            )
            PasswordConfirmTextField(
                value = "",
                password = password,
                onValueChange = {}
            )
            PasswordConfirmTextField(
                value = "qwerasdf",
                password = password,
                onValueChange = {}
            )
        }
    }
}