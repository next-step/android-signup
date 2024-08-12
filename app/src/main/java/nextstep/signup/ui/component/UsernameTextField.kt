package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.domain.UsernameValidType
import nextstep.signup.domain.UsernameValidator

@Composable
fun UsernameTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val supportingTextValue = if (value.isEmpty()) null
    else when (UsernameValidator.match(value)) {
        UsernameValidType.VALID -> null
        UsernameValidType.INVALID_LENGTH -> stringResource(id = R.string.username_length_error)
        UsernameValidType.INVALID_REGEX -> stringResource(id = R.string.username_regex_error)
    }

    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        labelValue = stringResource(id = R.string.username),
        supportingTextValue = supportingTextValue,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun UsernameTextFieldPreview() {
    var username by remember { mutableStateOf("jihoi") }

    UsernameTextField(
        value = username,
        onValueChange = { username = it },
    )
}

@Preview
@Composable
private fun EmptyUsernameTextFieldPreview() {
    var username by remember { mutableStateOf("") }

    UsernameTextField(
        value = username,
        onValueChange = { username = it },
    )
}

@Preview
@Composable
private fun InvalidUsernameTextFieldPreview1() {
    var username by remember { mutableStateOf("j") }

    UsernameTextField(
        value = username,
        onValueChange = { username = it },
    )
}

@Preview
@Composable
private fun InvalidUsernameTextFieldPreview2() {
    var username by remember { mutableStateOf("jihoik") }

    UsernameTextField(
        value = username,
        onValueChange = { username = it },
    )
}