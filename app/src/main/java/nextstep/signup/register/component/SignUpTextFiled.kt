package nextstep.signup.register.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.UserTextFiledColor

object SignUpTextFiled {

    @Composable
    operator fun invoke(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            UserName()
            Email()
            Password()
            PasswordConfirm()
        }
    }


    @Composable
    fun UserName(
        modifier: Modifier = Modifier,
        userName: String = EMPTY_STRING
    ) {
        var inputUserName by remember { mutableStateOf(userName) }

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
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
        email: String = EMPTY_STRING
    ) {
        var inputEmail by remember { mutableStateOf(email) }
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
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
        password: String = EMPTY_STRING
    ) {
        var inputPassword by remember { mutableStateOf(password) }

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
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
        passwordConfirm: String = EMPTY_STRING
    ) {
        var inputPasswordConfirm by remember { mutableStateOf(passwordConfirm) }

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            value = inputPasswordConfirm,
            onValueChange = { inputPasswordConfirm = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = UserTextFiledColor,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = stringResource(R.string.sign_up_hint_password_confirm)) }
        )
    }

    private const val EMPTY_STRING = ""
}

@Preview
@Composable
private fun PreviewSignUpTextFiled() {
    SignUpTextFiled()
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