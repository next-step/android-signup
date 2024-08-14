package nextstep.signup.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.TextFieldState
import nextstep.signup.model.TextFieldType
import nextstep.signup.model.setMessage
import nextstep.signup.view.ui.theme.Dimens.TextHelper
import nextstep.signup.view.ui.theme.Red50
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun PasswordConfirmTextField(
    onTextChange: (String) -> Unit,
    isMatchPassword: Boolean,
    modifier: Modifier = Modifier,
) {
    var input by remember { mutableStateOf("") }

    val validationState: TextFieldState = if (isMatchPassword) {
        TextFieldState.Valid
    } else {
        TextFieldState.InValid
    }

    val onValueChange = { value: String ->
        input = value
        onTextChange(value)
    }

    PasswordConfirmContent(input, onValueChange, validationState, modifier)
}

@Composable
fun PasswordConfirmContent(
    input: String,
    onValueChange: (String) -> Unit,
    validationState: TextFieldState,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier,
        value = input,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.password_confirm)) },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = if (validationState == TextFieldState.Valid) {
                MaterialTheme.colorScheme.primary
            } else {
                Red50
            }
        ),
        supportingText = {
            Text(
                text = setMessage(validationState, TextFieldType.PasswordConfirm),
                fontSize = TextHelper,
                color = Red50,
                textAlign = TextAlign.Start,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPasswordTextFieldPreview() {
    SignupTheme {
        PasswordConfirmTextField(
            onTextChange = { },
            isMatchPassword = true
        )
    }
}