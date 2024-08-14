package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.util.validation.ValidationErrorType
import nextstep.signup.util.validation.ValidationResult


@Composable
fun PasswordConfirmTextField(
    text: String,
    validationResult: ValidationResult,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_password_confirm_label),
        isError = validationResult is ValidationResult.Error,
        supportingText = {
            ValidationErrorText(
                validationResult = validationResult,
                equalityErrorResId = R.string.sign_up_user_password_confirm_not_equal
            )
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Preview(name = "정상 케이스", showBackground = true)
@Composable
private fun Preview1() {
    SignupTheme {
        PasswordConfirmTextField(
            text = "abcd1234",
            validationResult = ValidationResult.Success,
            onValueChange = {},
        )
    }
}

@Preview(name = "에러 케이스", showBackground = true)
@Composable
private fun Preview2() {
    SignupTheme {
        PasswordConfirmTextField(
            text = "abcd1234",
            validationResult = ValidationResult.Error(ValidationErrorType.EqualityError),
            onValueChange = {},
        )
    }
}