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
fun PasswordTextField(
    text: String,
    validationResult : ValidationResult,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_password_label),
        isError = validationResult is ValidationResult.Error,
        supportingText = {
            ValidationErrorText(
                validationResult = validationResult,
                lengthErrorResId = R.string.sign_up_user_password_length_error,
                regexErrorResId = R.string.sign_up_user_password_regex_error
            )
        },
        visualTransformation = PasswordVisualTransformation()
    )
}


@Preview(name = "정상 케이스",showBackground = true)
@Composable
private fun Preview1() {
    SignupTheme {
        PasswordTextField(
            text = "abcd1234",
            onValueChange = {},
            validationResult = ValidationResult.Success
        )
    }
}

@Preview(name = "글자수 에러 케이스",showBackground = true)
@Composable
private fun Preview2() {
    SignupTheme {
        PasswordTextField(
            text = "abc123",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.LengthError)
        )
    }
}

@Preview(name = "조건 에러 케이스 - 영문만",showBackground = true)
@Composable
private fun Preview3() {
    SignupTheme {
        PasswordTextField(
            text = "aaaaaaaa",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }
}

@Preview(name = "조건 에러 케이스 - 숫자만",showBackground = true)
@Composable
private fun Preview4() {
    SignupTheme {
        PasswordTextField(
            text = "123123123",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }
}