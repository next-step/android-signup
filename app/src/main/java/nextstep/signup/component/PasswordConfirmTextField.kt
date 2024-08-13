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
import nextstep.signup.util.validation.Validator


@Composable
fun PasswordConfirmTextField(
    text: String,
    target: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val validationResult by remember(text) {
        derivedStateOf {
            Validator.passwordConfirmValidate(
                password = target,
                passwordConfirm = text
            )
        }
    }

    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_password_confirm_label),
        isError = validationResult is ValidationResult.Error,
        supportingText = {
            signUpSupportingTextStringResource(
                validationResult = validationResult,
                signUpTextFieldType = SignUpTextFieldType.PasswordConfirm
            )?.let { supportingText ->
                Text(text = supportingText)
            }
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Preview(name = "정상 케이스",showBackground = true)
@Composable
private fun Preview1() {
    SignupTheme {
        PasswordConfirmTextField(
            text = "abcd1234",
            target = "abcd1234",
            onValueChange = {},
        )
    }
}

@Preview(name = "에러 케이스",showBackground = true)
@Composable
private fun Preview2() {
    SignupTheme {
        PasswordConfirmTextField(
            text = "abcd1234",
            target = "abcd123",
            onValueChange = {},
        )
    }
}