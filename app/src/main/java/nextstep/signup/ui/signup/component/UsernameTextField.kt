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
fun UsernameTextField(
    username: String,
    onUsernameChanged: (String) -> Unit,
    usernameValidation: SignupValidationResult,
) {
    val usernameLabel = stringResource(id = R.string.signup_user_name)
    val usernameErrorDescription = stringResource(R.string.signup_username_error)

    SignupTextField(
        text = username,
        onTextChanged = onUsernameChanged,
        label = usernameLabel,
        isError = usernameValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (usernameValidation !is Success)
                Text(
                    text = (usernameValidation as Failure).result.message,
                    modifier = Modifier.semantics { contentDescription = usernameErrorDescription },
                )
        },
        componentDescription = usernameLabel,
    )
}

@Preview
@Composable
private fun UsernameTextFieldPreview() {
    UsernameTextField(
        username = "s9hn",
        onUsernameChanged = {},
        usernameValidation = Success,
    )
}
