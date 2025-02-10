package nextstep.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.validator.SignupInfoValidator

@Composable
internal fun PasswordConfirmTextFiled(
    text: String,
    passwordProvider: () -> String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValidation: ((Boolean) -> Unit)? = null,
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_password_confirm),
        text = text,
        onValueChange = onValueChange,
        onValidation = { onValidation?.invoke(it) },
        visualTransformation = PasswordVisualTransformation(),
        validator = SignupInfoValidator.PasswordConfirm(passwordProvider),
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
            passwordProvider = { password },
            onValueChange = setPasswordConfirm,
        )
    }
}
