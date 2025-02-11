package nextstep.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun PasswordConfirmTextFiled(
    text: String,
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValidation: (Boolean) -> Unit = {},
) {
    val validator = remember(password) {
        SignupInfoValidator.PasswordConfirm { password }
    }

    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_password_confirm),
        text = text,
        onValueChange = {
            onValueChange(it)
            onValidation(validator.checkCondition(it).isSuccess())
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        validateResult = validator.checkCondition(text)

    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFiledPreview() {
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

    Column {
        PasswordTextFiled(
            text = password,
            onValueChange = setPassword
        )

        PasswordConfirmTextFiled(
            text = passwordConfirm,
            password = password,
            onValueChange = setPasswordConfirm,
        )
    }
}
