package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.util.validation.ValidationErrorType
import nextstep.signup.util.validation.ValidationResult

@Composable
fun EmailTextField(
    text: String,
    validationResult: ValidationResult,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_email_label),
        isError = validationResult is ValidationResult.Error,
        supportingText = {
            ValidationErrorText(
                validationResult = validationResult,
                regexErrorResId = R.string.sign_up_email_regex_error
            )
        }
    )
}

@Preview(name = "정상 케이스",showBackground = true)
@Composable
private fun Preview1() {
    SignupTheme {
        EmailTextField(
            text = "test@test.com",
            onValueChange = {},
            validationResult = ValidationResult.Success
        )
    }
}

@Preview(name = "조건 에러 케이스",showBackground = true)
@Composable
private fun Preview2() {
    SignupTheme {
        EmailTextField(
            text = "test!test.com",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }
}

@Preview(name = "조건 에러 케이스",showBackground = true)
@Composable
private fun Preview3() {
    SignupTheme {
        EmailTextField(
            text = "test@test,com",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }
}

@Preview(name = "조건 에러 케이스",showBackground = true)
@Composable
private fun Preview4() {
    SignupTheme {
        EmailTextField(
            text = "test@test.",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }
}