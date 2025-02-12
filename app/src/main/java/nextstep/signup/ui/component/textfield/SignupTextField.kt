package nextstep.signup.ui.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.Red50
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    secure: Boolean = false,
    validationState: ValidationState = ValidationState.None
) {
    TextField(
        modifier = modifier,
        value = value,
        label = { Text(label) },
        onValueChange = onValueChange,
        singleLine = true,
        isError = validationState is ValidationState.Error,
        supportingText = if (validationState is ValidationState.Error) {
            { Text(stringResource(validationState.resourceId)) }
        } else {
            null
        },
        colors = TextFieldDefaults.colors().copy(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20,
            errorLabelColor = Red50,
            errorSupportingTextColor = Red50,
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
        visualTransformation = if (secure) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = keyboardOptions,
    )
}

class SecureParameter : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}

@Composable
@Preview
fun SignupTextFieldPreview(
    @PreviewParameter(SecureParameter::class) secure: Boolean
) {
    val value = remember { mutableStateOf("") }
    SignupTheme {
        SignupTextField(
            value = value.value,
            onValueChange = { value.value = it },
            label = "Username",
            secure = true,
        )
    }
}
