package nextstep.signup.ui.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.R
import nextstep.signup.ui.common.component.SignupTextField
import nextstep.signup.ui.signup.SignupValidationResult
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordValidation: SignupValidationResult,
) {
    val passwordLabel = stringResource(R.string.signup_password)
    val passwordErrorDescription = stringResource(R.string.signup_password_error)

    SignupTextField(
        text = password,
        onTextChanged = onPasswordChanged,
        label = passwordLabel,
        isError = passwordValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (passwordValidation !is Success)
                Text(
                    text = (passwordValidation as Failure).result.message,
                    modifier = Modifier.semantics { contentDescription = passwordErrorDescription }
                )
        },
        componentDescription = passwordLabel,
    )
}
