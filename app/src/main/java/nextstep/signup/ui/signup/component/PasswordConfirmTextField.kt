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
fun PasswordConfirmTextField(
    passwordConfirm: String,
    onPasswordConfirmChanged: (String) -> Unit,
    passwordConfirmValidation: SignupValidationResult,
) {
    val passwordConfirmLabel = stringResource(R.string.signup_password_confirm)
    val passwordConfirmErrorDescription = stringResource(R.string.signup_password_confirm_error)

    SignupTextField(
        text = passwordConfirm,
        onTextChanged = onPasswordConfirmChanged,
        label = passwordConfirmLabel,
        isError = passwordConfirmValidation !is Success,
        visualTransformation = PasswordVisualTransformation(),
        supportingText = {
            if (passwordConfirmValidation !is Success)
                Text(
                    text = (passwordConfirmValidation as Failure).result.message,
                    modifier = Modifier.semantics {
                        contentDescription = passwordConfirmErrorDescription
                    },
                )
        },
        componentDescription = passwordConfirmLabel,
    )
}
