package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUpInput(
    value: String,
    onValueChange: (String) -> Unit,
    maxLine: Int = 1,
    isError: Boolean,
    supportingText: @Composable (() -> Unit)? = null,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLine,
        isError = isError,
        supportingText = supportingText,
        label = { Text(text = label) },
        modifier = modifier
    )
}