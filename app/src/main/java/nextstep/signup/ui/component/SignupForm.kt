package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.SignupBlue
import nextstep.signup.ui.util.ValidationResult
import nextstep.signup.ui.util.ValidationStates

@Composable
fun SignupForm(
    label: String = "",
    inputValue: String = "",
    onInputChange: (String) -> Unit = {},
    inputType: KeyboardType = KeyboardType.Text,
    validResult: ValidationResult,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = inputValue,
        onValueChange = { newText -> onInputChange(newText) },
        label = { Text(text = label) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = SignupBlue,
            unfocusedIndicatorColor = Color.Black,
            focusedLabelColor = SignupBlue,
            unfocusedLabelColor = Color.Black,
            cursorColor = SignupBlue,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = inputType
        ),
        visualTransformation = if (inputType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        isError = validResult.isValid().not(),
        supportingText = {
            if (validResult.isValid().not()) {
                Text(
                    text = stringResource(validResult.getErrorMessage()!!),
                    modifier = Modifier.height(16.dp),
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

class FormPreviewParameterProvider : PreviewParameterProvider<Pair<String, ValidationResult>> {
    private val successResult = ValidationResult(
        validationState = ValidationStates.SUCCESS,
    )

    override val values = sequenceOf(
        Pair("Username", successResult),
        Pair("Email", successResult),
        Pair("Password", successResult),
        Pair("Password Confirm", successResult),
    )
}

@Preview
@Composable
fun SignupFormPreview(
    @PreviewParameter(FormPreviewParameterProvider::class, limit = 4) type: Pair<String, ValidationResult>
) {
    SignupForm(
        label = type.first,
        validResult = type.second,
    )
}