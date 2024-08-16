package nextstep.signup.ui.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.common.component.SignupTextField
import nextstep.signup.ui.signup.SignupValidationResult
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success

@Composable
fun EmailTextField(
    email: String,
    onEmailChanged: (String) -> Unit,
    emailValidation: SignupValidationResult,
) {
    val emailLabel = stringResource(R.string.signup_email)
    val emailErrorDescription = stringResource(R.string.signup_email_error)

    SignupTextField(
        text = email,
        onTextChanged = onEmailChanged,
        label = emailLabel,
        isError = emailValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (emailValidation !is Success)
                Text(
                    text = (emailValidation as Failure).result.message,
                    modifier = Modifier.semantics { contentDescription = emailErrorDescription },
                )
        },
        componentDescription = emailLabel,
    )
}

@Preview
@Composable
private fun EmailTextFieldPreview() {
    EmailTextField(
        email = "s9hn@github.com",
        onEmailChanged = {},
        emailValidation = Success,
    )
}
