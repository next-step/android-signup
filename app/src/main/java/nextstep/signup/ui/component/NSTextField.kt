package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.R

@Composable
fun UserNameTextField(text: String, onTextValueChange: (String) -> Unit) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("UserName") },
            value = text,
            maxLines = 1,
            onValueChange = onTextValueChange
        )

        InputErrorText(
            stringResource(id = R.string.err_msg_user_name),
            !text.matches(Regex(stringResource(id = R.string.regex_user_name))) && text.isNotEmpty()
        )
    }
}

@Composable
fun EmailTextField(text: String, onTextValueChange: (String) -> Unit) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            value = text,
            maxLines = 1,
            onValueChange = onTextValueChange
        )

        InputErrorText(
            stringResource(id = R.string.err_msg_email),
            !text.matches(Regex(stringResource(id = R.string.regex_email))) && text.isNotEmpty()
        )
    }
}

@Composable
fun PasswordTextField(text: String, onTextValueChange: (String) -> Unit) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            value = text,
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            onValueChange = onTextValueChange
        )
        InputErrorText(
            stringResource(id = R.string.err_msg_password),
            !text.matches(Regex(stringResource(id = R.string.regex_password))) && text.isNotEmpty()
        )
    }
}

@Composable
fun PasswordConfirmTextField(text: String, onTextValueChange: (String) -> Unit, password: String) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("PasswordConfirm") },
            value = text,
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            onValueChange = onTextValueChange
        )
        InputErrorText(
            stringResource(id = R.string.err_msg_password_confirm),
            password != text && text.isNotEmpty()
        )
    }
}

