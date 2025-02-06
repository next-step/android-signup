package nextstep.signup.register.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.theme.UserTextFiledColor

object SignUpTextFiled {

    @Composable
    fun UserName(
        modifier: Modifier = Modifier,
        userName: String
    ) {
        var inputUserName by remember { mutableStateOf(userName) }

        TextField(
            modifier = modifier,
            value = inputUserName,
            onValueChange = { inputUserName = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = UserTextFiledColor,
            label = { Text(text = stringResource(R.string.sign_up_hint_username)) }
        )
    }

    @Composable
    fun Email(
        modifier: Modifier = Modifier,
        email: String
    ) {
        var inputEmail by remember { mutableStateOf(email) }
        TextField(
            modifier = modifier,
            value = inputEmail,
            onValueChange = { inputEmail = it },
            singleLine = true,
            colors = UserTextFiledColor,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = stringResource(R.string.sign_up_hint_email)) }
        )
    }

    @Composable
    fun Password(
        modifier: Modifier = Modifier,
        password: String
    ) {
        var inputPassword by remember { mutableStateOf(password) }

        TextField(
            modifier = modifier,
            value = inputPassword,
            onValueChange = { inputPassword = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = UserTextFiledColor,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = stringResource(R.string.sign_up_hint_password)) }
        )
    }


    @Composable
    fun PasswordConfirm(
        modifier: Modifier = Modifier,
        passwordConfirm: String
    ) {
        var inputPasswordConfirm by remember { mutableStateOf(passwordConfirm) }

        TextField(
            modifier = modifier,
            value = inputPasswordConfirm,
            onValueChange = { inputPasswordConfirm = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = UserTextFiledColor,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = stringResource(R.string.sign_up_hint_password_confirm)) }
        )
    }
}

@Preview
@Composable
private fun PreviewUserNameTextFiled() {
    SignUpTextFiled.UserName(
        modifier = Modifier.fillMaxWidth(),
        userName = "김컴포즈"
    )
}

@Preview
@Composable
private fun PreviewEmailTextFiled() {
    SignUpTextFiled.Email(
        modifier = Modifier.fillMaxWidth(),
        email = "test@test.com"
    )
}

@Preview
@Composable
private fun PreviewPasswordTextFiled() {
    SignUpTextFiled.Password(
        modifier = Modifier.fillMaxWidth(),
        password = "김컴포즈1234"
    )
}


@Preview
@Composable
private fun PreviewPasswordConfirmTextFiled() {
    SignUpTextFiled.PasswordConfirm(
        modifier = Modifier.fillMaxWidth(),
        passwordConfirm = "김컴포즈1234"
    )
}