package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.util.SignUpValidationCheck


@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    val validation by remember(email) {
        derivedStateOf { SignUpValidationCheck.validateEmail(email) }
    }

    TextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.email)) },
        supportingText = {
            when (validation) {
                SignUpValidation.INVALID_EMAIL ->
                    Text(text = stringResource(id = R.string.error_invalid_email))
                else -> {}
            }
        },
        isError = email.isNotBlank() && validation != SignUpValidation.NONE,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailFieldPreview() {
    var emptyEmail by remember { mutableStateOf("") }
    var validEmail by remember { mutableStateOf("kyudong3@gmail.com") }
    var invalidEmail by remember { mutableStateOf("kyudong3@gmail") }
    Column {
        EmailField(
            email = emptyEmail,
            onEmailChange = { emptyEmail = it }
        )
        EmailField(
            email = validEmail,
            onEmailChange = { validEmail = it }
        )
        EmailField(
            email = invalidEmail,
            onEmailChange = { invalidEmail = it }
        )
    }
}
