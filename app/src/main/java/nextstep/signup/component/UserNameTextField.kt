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
import nextstep.signup.util.validation.ValidationResult
import nextstep.signup.util.validation.ValidationUtil


@Composable
fun UserNameTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val validationResult by remember(text) {
        derivedStateOf { ValidationUtil.userNameValidate(text) }
    }

    SignUpTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = stringResource(id = R.string.sign_up_user_name_label),
        isError = validationResult is ValidationResult.ValidationError,
        supportingText = {
            signUpSupportingTextStringResource(
                validationResult = validationResult,
                signUpTextFieldType = SignUpTextFieldType.UserName
            )?.let { supportingText ->
                Text(text = supportingText)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview() {
    SignupTheme {
        UserNameTextField(
            text = "Test",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreviewAsLengthError() {
    SignupTheme {
        UserNameTextField(
            text = "김컴포즈입니다",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreviewAsRegexError() {
    SignupTheme {
        UserNameTextField(
            text = "23",
            onValueChange = {},
        )
    }
}