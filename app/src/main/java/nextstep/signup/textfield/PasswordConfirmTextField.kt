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
    isPasswordMatched: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SignUpTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.sign_up_password_confirm),
        errorMessage = if (value.isNotEmpty() && isPasswordMatched.not()) stringResource(R.string.sign_up_password_confirm_not_matched_error) else null,
        visualTransformation = PasswordVisualTransformation()
    )
}


@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview() {
    SignupTheme {
        Column {
            PasswordConfirmTextField(
                value = "",
                isPasswordMatched = true,
                onValueChange = {}
            )
            PasswordConfirmTextField(
                value = "qwer1234!@#$",
                isPasswordMatched = true,
                onValueChange = {}
            )
            PasswordConfirmTextField(
                value = "qwerasdf",
                isPasswordMatched = false,
                onValueChange = {}
            )
        }
    }
}