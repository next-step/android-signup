package nextstep.signup.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.domain.EmailValidType
import nextstep.signup.domain.EmailValidator

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val supportingTextValue = if (value.isEmpty()) null
    else when (EmailValidator.match(value)) {
        EmailValidType.VALID -> null
        EmailValidType.INVALID_REGEX -> stringResource(id = R.string.email_regex_error)
    }

    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        labelValue = stringResource(id = R.string.email),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        supportingTextValue = supportingTextValue,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun EmailTextFieldPreview() {
    var email by remember { mutableStateOf("jihoi.kang@gmail.com") }

    EmailTextField(
        value = email,
        onValueChange = { email = it },
    )
}

@Preview
@Composable
private fun EmptyEmailTextFieldPreview() {
    var email by remember { mutableStateOf("") }

    EmailTextField(
        value = email,
        onValueChange = { email = it },
    )
}

@Preview
@Composable
private fun InvalidEmailTextFieldPreview1() {
    var email by remember { mutableStateOf("jihoi.kang") }

    EmailTextField(
        value = email,
        onValueChange = { email = it },
    )
}

@Preview
@Composable
private fun InvalidEmailTextFieldPreview2() {
    var email by remember { mutableStateOf("jihoi.kang@aa.a") }

    EmailTextField(
        value = email,
        onValueChange = { email = it },
    )
}