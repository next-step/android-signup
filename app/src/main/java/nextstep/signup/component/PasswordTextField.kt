package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.screen.SignUpTextFieldType
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.util.validation.ValidationResult
import nextstep.signup.util.validation.ValidationUtil


@Composable
fun PasswordTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val validationResult by remember(text) {
        derivedStateOf { ValidationUtil.passwordValidate(text) }
    }

    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_password_label),
        isError = validationResult is ValidationResult.ValidationError,
        supportingText = {
            signUpSupportingTextStringResource(
                validationResult = validationResult,
                signUpTextFieldType = SignUpTextFieldType.Password
            )?.let { supportingText ->
                Text(text = supportingText)
            }
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    SignupTheme {
        UserNameTextField(
            text = "Test",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreviewAsLengthError() {
    SignupTheme {
        PasswordTextField(
            text = "abc123",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreviewAsRegexError() {
    SignupTheme {
        PasswordTextField(
            text = "aaaaaaaa",
            onValueChange = {},
        )
    }
}