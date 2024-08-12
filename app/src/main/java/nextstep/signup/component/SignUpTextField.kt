package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.valid.RegexBasedSignUpValidator
import nextstep.signup.valid.SignUpValidator
import nextstep.signup.model.EmailState
import nextstep.signup.model.PasswordConfirmState
import nextstep.signup.model.PasswordError
import nextstep.signup.model.PasswordState
import nextstep.signup.model.UserNameError
import nextstep.signup.model.UserNameState

@Composable
private fun SignUpTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    val customColors = TextFieldDefaults.colors(
        focusedIndicatorColor = colorResource(id = R.color.blue_50),
        cursorColor = colorResource(id = R.color.blue_50),
        focusedLabelColor = colorResource(id = R.color.blue_50),
        errorIndicatorColor = colorResource(id = R.color.warning_text_color),
        errorLabelColor = colorResource(id = R.color.warning_text_color),
        errorCursorColor = colorResource(id = R.color.warning_text_color)
    )
    TextField(value = text,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = customColors,
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = errorMessage, color = colorResource(id = R.color.warning_text_color))
            }
        })
}

@Composable
fun SignUpFormComponent(
    userName: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    validator: SignUpValidator = RegexBasedSignUpValidator()
) {
    val userNameState by remember(userName) {
        derivedStateOf { validator.validateUsername(userName) }
    }
    val emailState by remember(email) {
        derivedStateOf { validator.validateEmail(email) }
    }
    val passwordState by remember(password) {
        derivedStateOf { validator.validatePassword(password) }
    }
    val passwordConfirmState by remember(password, passwordConfirm) {
        derivedStateOf { validator.validatePasswordConfirm(password, passwordConfirm) }
    }
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {

        SignUpTextField(
            text = userName,
            label = stringResource(R.string.sign_up_text_field_username),
            onValueChange = onUserNameChange,
            isError = userNameState is UserNameState.Invalid,
            errorMessage = when (userNameState) {
                is UserNameState.Invalid -> when ((userNameState as UserNameState.Invalid).error) {
                    UserNameError.Length -> stringResource(R.string.sign_up_error_name_length)
                    UserNameError.InvalidCharacters -> stringResource(R.string.sign_up_error_name_invalid_chars)
                }
                UserNameState.Valid -> ""
                UserNameState.Initial -> ""
            }
        )

        SignUpTextField(
            text = email,
            label = stringResource(R.string.sign_up_text_field_email),
            onValueChange = onEmailChange,
            isError = emailState is EmailState.Invalid,
            errorMessage = when (emailState) {
                EmailState.Invalid -> stringResource(R.string.sig_nup_error_email_invalid)
                EmailState.Valid -> ""
                EmailState.Initial -> ""
            }
        )

        SignUpTextField(
            text = password,
            label = stringResource(R.string.sign_up_text_field_password),
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordState is PasswordState.Invalid,
            errorMessage = when (passwordState) {
                is PasswordState.Invalid -> when ((passwordState as PasswordState.Invalid).error) {
                    PasswordError.Length -> stringResource(R.string.sign_up_error_password_length)
                    PasswordError.Complexity -> stringResource(R.string.sign_up_error_password_requirement)
                }
                PasswordState.Valid -> ""
                PasswordState.Initial -> ""
            }
        )

        SignUpTextField(
            text = passwordConfirm,
            label = stringResource(R.string.sign_up_text_field_password_confirm),
            onValueChange = onPasswordConfirmChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordConfirmState is PasswordConfirmState.Mismatch,
            errorMessage = when (passwordConfirmState) {
                PasswordConfirmState.Mismatch -> stringResource(R.string.sign_up_error_password_mismatch)
                PasswordConfirmState.Valid -> ""
                PasswordConfirmState.Initial -> ""
            }
        )
    }
}

@Preview
@Composable
private fun PreviewSignUpTextField() {
    SignUpTextField(text = "compose", onValueChange = {}, label = "Username")
}

@Preview
@Composable
private fun PreviewSignUpFormComponent() {
    SignUpFormComponent(userName = "compose",
        email = "test@test.com",
        password = "12345678",
        passwordConfirm = "12345678",
        onUserNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onPasswordConfirmChange = {})
}