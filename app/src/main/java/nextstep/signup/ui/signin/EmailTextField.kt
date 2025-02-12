package nextstep.signup.ui.signin

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.SignupValidator.ResultType
import nextstep.signup.ui.component.SignupTextField

@Composable
fun EmailTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    validationResult: ResultType,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Default,
) {
    val isError = remember(validationResult) {
        validationResult != ResultType.Success && validationResult != ResultType.Empty
    }
    val errorMessage = when (validationResult) {
        ResultType.EmailInvalidFormat -> stringResource(R.string.signup_error_email_invalid_format)
        else -> ""
    }

    SignupTextField(
        label = label,
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        ),
        isError = isError,
        errorMessage = errorMessage
    )
}

@Preview
@Composable
fun EmailTextFieldPreview() {
    EmailTextField(
        label = "Email",
        value = "abc#abc.com",
        onValueChange = { },
        validationResult = ResultType.EmailInvalidFormat,
    )
}