package nextstep.signup.ui.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.util.ValidationResult

@Composable
fun PasswordTextField(
    label: String = "",
    inputValue: String = "",
    onInputChange: (String) -> Unit = {},
    validResult: ValidationResult,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = inputValue,
        onValueChange = { newText -> onInputChange(newText) },
        label = { Text(text = label) },
        colors = commonTextFieldColors(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        visualTransformation = PasswordVisualTransformation(),
        isError = validResult is ValidationResult.Invalid,
        supportingText = {
            if (validResult is ValidationResult.Invalid) {
                Text(
                    text = stringResource(validResult.messageRes),
                    modifier = Modifier.height(16.dp),
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(
        label = "Password",
        inputValue = "sample",
        validResult = ValidationResult.Correct,
    )
}