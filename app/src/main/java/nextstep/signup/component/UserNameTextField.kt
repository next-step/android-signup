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
fun UserNameTextField(
    text: String,
    validationResult: ValidationResult,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_user_name_label),
        isError = validationResult is ValidationResult.Error,
        supportingText = {
            validationResult.getErrorType()?.let { type ->
                when (type) {
                    ValidationErrorType.LengthError ->
                        Text(text = stringResource(id = R.string.sign_up_user_name_length_error))
                    ValidationErrorType.RegexError ->
                        Text(text = stringResource(id = R.string.sign_up_user_name_regex_error))
                    else -> {
                    }
                }
            }
        }
    )
}

@Preview(name = "정상 케이스", showBackground = true)
@Composable
private fun Preview1() {
    SignupTheme {
        UserNameTextField(
            text = "Test",
            onValueChange = {},
            validationResult = ValidationResult.Pending
        )
    }
}

@Preview(name = "글자수 에러 케이스", showBackground = true)
@Composable
private fun Preview2() {
    SignupTheme {
        UserNameTextField(
            text = "김컴포즈입니다",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.LengthError)
        )
    }
}

@Preview(name = "조건 에러 케이스", showBackground = true)
@Composable
private fun Preview3() {
    SignupTheme {
        UserNameTextField(
            text = "23",
            onValueChange = {},
            validationResult = ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }
}