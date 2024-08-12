package nextstep.signup.component.textField

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.EmailState

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    emailState: EmailState
) {
    BaseSignUpTextField(
        text = email,
        onValueChange = onEmailChange,
        label = stringResource(R.string.sign_up_text_field_email),
        isError = emailState is EmailState.Invalid,
        errorMessage = when (emailState) {
            EmailState.Invalid -> stringResource(R.string.sig_nup_error_email_invalid)
            EmailState.Valid, EmailState.Initial -> ""
        }
    )
}
