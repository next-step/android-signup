package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.screen.SignUpTextFieldType
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.util.validation.ValidationErrorType
import nextstep.signup.util.validation.ValidationResult
import nextstep.signup.util.validation.Validator

@Composable
fun EmailTextField(
    text: String,
    onValueChange: (String) -> Unit,
    validationResult: ValidationResult,
    modifier: Modifier = Modifier,
) {
    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_email_label),
        isError = validationResult is ValidationResult.Error,
        supportingText = {
            validationResult.getErrorType()?.let { type ->
                when (type) {
                   ValidationErrorType.RegexError ->
                        Text(text =   stringResource(id = R.string.sign_up_email_regex_error))
                    else -> {
                    }
                }
            }
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