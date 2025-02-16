package nextstep.signup.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme


@Composable
fun SignUpInputForm(
    placeHolderText: String,
    keyboardType: KeyboardType,
    errorMessage: String,
    inputText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    ) {
    Column {
        TextField(
            value = inputText,
            onValueChange = onValueChange,
            label = { Text(placeHolderText) },
            visualTransformation = visualTransformation,
            singleLine = true,
            placeholder = { Text(placeHolderText) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE3E8F1),
                unfocusedContainerColor = Color(0xFFE3E8F1)
            ),
            isError = errorMessage.isNotEmpty(),
        )
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpInputFormPreview() {
    var nameFieldValue by remember { mutableStateOf("") }
    SignupTheme {
        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_email),
            keyboardType = KeyboardType.Text,
            inputText = nameFieldValue,
            onValueChange = { newTextFieldValue ->
                nameFieldValue = newTextFieldValue
            },
            errorMessage = "error 문구"
        )
    }
}