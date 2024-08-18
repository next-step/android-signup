package nextstep.signup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.util.ValidationPatterns.EMAIL_REGEX

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    InputTextField(
        modifier = Modifier.padding(top = 6.dp),
        value = email,
        onValueChange = onEmailChange,
        label = stringResource(R.string.sign_up_label_email),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        errorMessage = getEmailErrorMessage(email)
    )
}

@Composable
fun getEmailErrorMessage(email: String): String {
    return when {
        email.isEmpty() -> ""
        !email.matches(Regex(EMAIL_REGEX)) -> {
            stringResource(id = R.string.sign_up_email_error)
        }
        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview() {
    EmailTextField(email = "thxallgrace@gmail.com") {}
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldErrorPreview() {
    EmailTextField(email = "thxallgrace@gmail") {}
}


