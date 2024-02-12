package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
internal fun PasswordConfirmTextField(
    password: String,
    confirmPassword: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = remember(password, confirmPassword) {
        when {
            confirmPassword.isEmpty() -> null
            password != confirmPassword -> {
                { Text(text = stringResource(id = R.string.signup_password_confirm_diff_error)) }
            }
            else -> null
        }
    }

    TextField(
        value = confirmPassword,
        onValueChange = onPasswordChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.signup_password_confirm_placeholder)) },
        supportingText = supportingText,
        isError = supportingText != null,
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

private class PasswordConfirmTextFieldPreviewParameter :
    PreviewParameterProvider<Pair<String, String>> {
    override val values: Sequence<Pair<String, String>>
        get() = sequenceOf(
            "" to "",
            "1q2w3e4r!" to "1q2w3e4r!",
            "1234" to "5678",
        )
}


@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview(
    @PreviewParameter(PasswordConfirmTextFieldPreviewParameter::class)
    parameter: Pair<String, String>,
) {
    MaterialTheme {
        PasswordConfirmTextField(
            password = parameter.first,
            confirmPassword = parameter.second,
            onPasswordChange = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
