package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun UsernameField(
    username: String,
    onUsernameChange: (String) -> Unit
) {
    val errorCondition = username.length !in 2..5 && username.isNotBlank()

    TextField(
        value = username,
        onValueChange = { onUsernameChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Username") },
        placeholder = { Text(text = "Username") },
        supportingText = {
            if (errorCondition) {
                Text(text = "이름은 2~5자여야 합니다.")
            }
        },
        isError = errorCondition
    )
}

@Preview(showBackground = true)
@Composable
private fun UsernameFieldPreview() {
    var username by remember { mutableStateOf("") }
    UsernameField(
        username = username,
        onUsernameChange = { username = it }
    )
}
