package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.ui.ThemePreviews
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label,
        isError = supportingText != null,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        visualTransformation = visualTransformation,
        supportingText = {
            supportingText?.let {
                CompositionLocalProvider(
                    LocalContentColor provides MaterialTheme.colorScheme.error,
                    LocalTextStyle provides MaterialTheme.typography.labelSmall,
                    content = it
                )
            }
        }
    )
}

@ThemePreviews
@Composable
private fun SignUpTextFieldPreview() {
    SignupTheme {
        SignUpTextField(
            value = "이지훈",
            onValueChange = {},
            label = { Text(text = "이름") }
        )
    }
}

@ThemePreviews
@Composable
private fun SignUpTextFieldErrorPreview() {
    SignupTheme {
        SignUpTextField(
            value = "이지훈",
            onValueChange = {},
            label = { Text(text = "이름") },
            supportingText = { Text(text = "error") }
        )
    }
}
