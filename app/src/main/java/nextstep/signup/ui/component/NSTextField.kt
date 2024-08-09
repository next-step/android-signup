package nextstep.signup.ui.component

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
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(id = R.string.user_name)) },
        value = text,
        maxLines = 1,
        onValueChange = onTextValueChange
    )
}

@Composable
fun EmailTextField(text: String, onTextValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(id = R.string.email)) },
        value = text,
        maxLines = 1,
        onValueChange = onTextValueChange
    )
}

@Composable
fun PasswordTextField(text: String, onTextValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(id = R.string.password)) },
        value = text,
        visualTransformation = PasswordVisualTransformation(),
        maxLines = 1,
        onValueChange = onTextValueChange
    )
}

@Composable
fun PasswordConfirmTextField(text: String, onTextValueChange: (String) -> Unit, password: String) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(id = R.string.password_confirm)) },
        value = text,
        visualTransformation = PasswordVisualTransformation(),
        maxLines = 1,
        onValueChange = onTextValueChange
    )
}

