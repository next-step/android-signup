package nextstep.signup.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordMatch
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid

@Composable
fun SignUpContents(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var nameFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var nameErrorMessage by remember { mutableStateOf("") }
        var emailFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var emailErrorMessage by remember { mutableStateOf("") }
        var passwordFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var passwordErrorMessage by remember { mutableStateOf("") }
        var passwordConfirmFieldValue by remember { mutableStateOf(TextFieldValue("")) }

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_name),
            keyboardType = KeyboardType.Text,
            textFieldValue = nameFieldValue,
            onValueChange = { newTextFieldValue ->
                nameFieldValue = newTextFieldValue
                nameErrorMessage = isNameValid(newTextFieldValue.text)
            },
            errorMessage = nameErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_email),
            keyboardType = KeyboardType.Text,
            textFieldValue = emailFieldValue,
            onValueChange = { newTextFieldValue ->
                emailFieldValue = newTextFieldValue
                emailErrorMessage = isEmailValid(newTextFieldValue.text)
            }, errorMessage = emailErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            textFieldValue = passwordFieldValue,
            onValueChange = { newTextFieldValue ->
                passwordFieldValue = newTextFieldValue
                passwordErrorMessage = isPasswordValid(newTextFieldValue.text)
            },
            errorMessage = passwordErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password_confirm),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            textFieldValue = passwordConfirmFieldValue,
            onValueChange = { newTextFieldValue ->
                passwordConfirmFieldValue = newTextFieldValue
            },
            errorMessage = isPasswordMatch(passwordFieldValue.text, passwordConfirmFieldValue.text)
        )

        SignUpButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            buttonTitle = stringResource(R.string.signup_main_signtup_button),
            onClick = {})
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpContentsPreview() {
    SignUpContents()
}