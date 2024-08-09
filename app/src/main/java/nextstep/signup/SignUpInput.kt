package nextstep.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpInput(
    value: String,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        isError = errorMessage.isNotEmpty(),
        maxLines = 1,
        supportingText = {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage)
            }
        },
        visualTransformation = visualTransformation,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    )
}

@Preview
@Composable
private fun SignUpInputPreview() {
    SignupTheme {
        SignUpInput(
            value = "text",
            errorMessage = "",
            onValueChange = { },
            label = stringResource(id = R.string.userNameLabel),
        )
    }
}