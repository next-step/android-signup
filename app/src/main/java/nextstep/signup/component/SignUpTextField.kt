package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.screen.SignUpTextFieldType
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray10
import nextstep.signup.ui.theme.Gray40
import nextstep.signup.ui.theme.Red40
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.util.validation.ValidationErrorType
import nextstep.signup.util.validation.ValidationResult

@Composable
fun SignUpTextField(
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    isError: Boolean = false,
    supportingText: @Composable () -> Unit = {},
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedLabelColor = Blue50,
        focusedIndicatorColor = Blue50,
        unfocusedLabelColor = Gray40,
        cursorColor = Blue50,
        focusedTextColor = Gray10,
        unfocusedTextColor = Gray10,
        errorTextColor = Red40
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        label = {
            Text(
                text = labelText
            )
        },
        supportingText = supportingText,
        colors = colors,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        isError = isError,
        visualTransformation = visualTransformation
    )
}


@Composable
fun signUpSupportingTextStringResource(
    signUpTextFieldType: SignUpTextFieldType,
    validationResult: ValidationResult
): String? {
    return if (validationResult is ValidationResult.ValidationError) {
        when (signUpTextFieldType) {
            SignUpTextFieldType.UserName -> {
                when (validationResult.type) {
                    ValidationErrorType.LengthError ->
                        stringResource(id = R.string.sign_up_user_name_length_error)

                    ValidationErrorType.RegexError ->
                        stringResource(id = R.string.sign_up_user_name_regex_error)
                    else -> null
                }
            }
            SignUpTextFieldType.Email -> {
                if(validationResult.type == ValidationErrorType.RegexError) {
                    stringResource(id = R.string.sign_up_email_regex_error)
                }
                else null
            }
            SignUpTextFieldType.Password ->{
                when (validationResult.type) {
                    ValidationErrorType.LengthError ->
                        stringResource(id = R.string.sign_up_user_password_length_error)

                    ValidationErrorType.RegexError ->
                        stringResource(id = R.string.sign_up_user_password_regex_error)
                    else -> null
                }
            }
            SignUpTextFieldType.PasswordConfirm ->{
                if(validationResult.type == ValidationErrorType.EqualityError) {
                    stringResource(id = R.string.sign_up_user_password_confirm_not_equal)
                }
                else null
            }
        }

    } else null
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextFieldPreview() {
    SignupTheme {
        SignUpTextField(
            text = "",
            labelText = "Preview",
            onValueChange = {}
        )
    }
}


