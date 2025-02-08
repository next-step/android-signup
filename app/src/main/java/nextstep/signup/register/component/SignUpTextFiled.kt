package nextstep.signup.register.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.register.constants.SignUpValidation.EMAIL_REGEX
import nextstep.signup.register.constants.SignUpValidation.PASSWORD_LENGTH_REGEX
import nextstep.signup.register.constants.SignUpValidation.PASSWORD_REGEX
import nextstep.signup.register.constants.SignUpValidation.USERNAME_REGEX
import nextstep.signup.register.constants.SignUpValidation.USER_LENGTH_REGEX
import nextstep.signup.ui.theme.Error
import nextstep.signup.ui.theme.UserTextFiledColor

object SignUpTextFiled {

    @Composable
    operator fun invoke(modifier: Modifier = Modifier) {

        var inputUserName by remember { mutableStateOf(EMPTY_STRING) }
        var inputEmail by remember { mutableStateOf(EMPTY_STRING) }
        var inputPassword by remember { mutableStateOf(EMPTY_STRING) }
        var inputPasswordConfirm by remember { mutableStateOf(EMPTY_STRING) }

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            UserName(
                userName = inputUserName,
                onChangedName = { inputUserName = it }
            )
            Email(
                email = inputEmail,
                onChangedEmail = { inputEmail = it }
            )
            Password(
                password = inputPassword,
                onChangedPassword = { inputPassword = it }
            )
            PasswordConfirm(
                passwordConfirm = inputPasswordConfirm,
                onChangedPasswordConfirm = { inputPasswordConfirm = it },
                isShowError = (inputPassword != inputPasswordConfirm) && inputPasswordConfirm.isNotEmpty()
            )
        }
    }


    @Composable
    fun UserName(
        modifier: Modifier = Modifier,
        userName: String = EMPTY_STRING,
        onChangedName: (String) -> Unit = {}
    ) {
        val isShowError =
            (!userName.matches(Regex(USERNAME_REGEX)) ||
                    !userName.matches(Regex(USER_LENGTH_REGEX))) &&
                    userName.isNotEmpty()

        val errorMessage = when {
            !userName.matches(Regex(USER_LENGTH_REGEX)) -> {
                stringResource(R.string.sign_up_error_username_length)
            }

            !userName.matches(Regex(USERNAME_REGEX)) -> {
                stringResource(R.string.sign_up_error_username)
            }

            else -> EMPTY_STRING
        }

        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = userName,
            isError = isShowError,
            onValueChange = onChangedName,
            singleLine = true,
            supportingText = {
                if (isShowError) {
                    SignUpError(text = errorMessage)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = UserTextFiledColor,
            label = { Text(text = stringResource(R.string.sign_up_hint_username)) }
        )
    }

    @Composable
    fun Email(
        modifier: Modifier = Modifier,
        email: String = EMPTY_STRING,
        onChangedEmail: (String) -> Unit = {}
    ) {
        val isShowError = !email.matches(Regex(EMAIL_REGEX)) && email.isNotEmpty()

        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = email,
            onValueChange = onChangedEmail,
            singleLine = true,
            isError = isShowError,
            supportingText = {
                if (isShowError) {
                    SignUpError(text = stringResource(R.string.sign_up_error_email))
                }
            },
            colors = UserTextFiledColor,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = stringResource(R.string.sign_up_hint_email)) }
        )
    }

    @Composable
    fun Password(
        modifier: Modifier = Modifier,
        password: String = EMPTY_STRING,
        onChangedPassword: (String) -> Unit = {}
    ) {

        val isShowError =
            (!password.matches(Regex(PASSWORD_REGEX)) ||
                    !password.matches(Regex(PASSWORD_LENGTH_REGEX))) &&
                    password.isNotEmpty()

        val errorMessage = when {
            !password.matches(Regex(PASSWORD_LENGTH_REGEX)) -> {
                stringResource(R.string.sign_up_error_password_length)
            }

            !password.matches(Regex(PASSWORD_REGEX)) -> {
                stringResource(R.string.sign_up_error_password)
            }

            else -> EMPTY_STRING
        }

        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = password,
            onValueChange = onChangedPassword,
            singleLine = true,
            isError = isShowError,
            supportingText = {
                if (isShowError) {
                    SignUpError(text = errorMessage)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = UserTextFiledColor,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = stringResource(R.string.sign_up_hint_password)) }
        )
    }


    @Composable
    fun PasswordConfirm(
        modifier: Modifier = Modifier,
        passwordConfirm: String = EMPTY_STRING,
        onChangedPasswordConfirm: (String) -> Unit = {},
        isShowError: Boolean = false
    ) {

        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = passwordConfirm,
            onValueChange = onChangedPasswordConfirm,
            singleLine = true,
            isError = isShowError,
            supportingText = {
                if (isShowError) {
                    SignUpError(text = stringResource(R.string.sign_up_error_password_confirm))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = UserTextFiledColor,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = stringResource(R.string.sign_up_hint_password_confirm)) }
        )
    }


    @Composable
    private fun SignUpError(
        modifier: Modifier = Modifier,
        text: String
    ) {
        Text(
            modifier = modifier,
            text = text,
            maxLines = 1,
            fontSize = 12.sp,
            color = Error
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
    SignUpTextFiled.UserName(userName = "김컴포즈")
}

@Preview
@Composable
private fun PreviewEmailTextFiled() {
    SignUpTextFiled.Email(email = "test@test.com")
}

@Preview
@Composable
private fun PreviewPasswordTextFiled() {
    SignUpTextFiled.Password(password = "김컴포즈1234")
}


@Preview
@Composable
private fun PreviewPasswordConfirmTextFiled() {
    SignUpTextFiled.PasswordConfirm(passwordConfirm = "김컴포즈1234")
}