package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
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
}
