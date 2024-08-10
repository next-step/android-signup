package nextstep.signup.ui.component.inputField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.util.SignUpInputValidation

@Composable
fun PasswordInputField(
    password: String,
    onValueChange: (String) -> Unit,
    space: Dp = 0.dp,
    passwordHint: String = stringResource(R.string.sign_up_password),
    passwordConfirmHint: String = stringResource(R.string.sign_up_password_confirm),
) {
    val context = LocalContext.current
    var passwordConfirm by remember { mutableStateOf("") }
    val passwordSupportingText by remember(password) {
        derivedStateOf {
            when {
                password.isBlank() -> ""
                !password.matches(SignUpInputValidation.PASSWORD_LENGTH_REGEX.toRegex()) -> context.getString(R.string.sign_up_password_input_validation_length_message)
                !password.matches(SignUpInputValidation.PASSWORD_REGEX.toRegex()) -> context.getString(R.string.sign_up_password_input_validation_message)
                else -> ""
            }
        }
    }
    val passwordConfirmSupportingText by remember(password, passwordConfirm) {
        derivedStateOf {
            when {
                passwordConfirm.isBlank() -> ""
                passwordConfirm != password -> context.getString(R.string.sign_up_password_not_equals_password_confirm_message)
                else -> ""
            }
        }
    }

    Column {
        // password
        TextField(
            modifier = Modifier
                .testTag("password")
                .fillMaxWidth(),
            value = password,
            onValueChange = onValueChange,
            label = { Text(passwordHint) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            supportingText = { Text(passwordSupportingText) },
            isError = passwordSupportingText.isNotBlank(),
        )

        Spacer(modifier = Modifier.height(space))

        // passwordConfirm
        TextField(
            modifier = Modifier
                .testTag("passwordConfirm")
                .fillMaxWidth(),
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            label = { Text(passwordConfirmHint) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            supportingText = { Text(passwordConfirmSupportingText) },
            isError = passwordConfirmSupportingText.isNotBlank(),
        )
    }
}

@Preview
@Composable
private fun PasswordInputFieldPreview() {
    PasswordInputField(
        password = "",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun PasswordInputFieldPreviewWithValue() {
    PasswordInputField(
        password = "1q2w3e4r",
        onValueChange = {},
    )
}

@Preview
@Composable
private fun PasswordInputFieldPreviewErrorShortPassword() {
    PasswordInputField(
        password = "1q2w3e",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun PasswordInputFieldPreviewErrorMustIncludeEnglishAndNumber() {
    PasswordInputField(
        password = "1234567890",
        onValueChange = {}
    )
}
